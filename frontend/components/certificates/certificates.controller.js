import alertify from 'alertifyjs';
import iziToast from 'izitoast';

export default class {

  constructor(CertificatesService, LocalStorageService, $state, $translate, UserService, PurchaseService, $filter) {
    'ngInject';

    this.$filter = $filter;
    this.$translate = $translate;
    this.$state = $state;
    this.PurchaseService = PurchaseService;
    this.CertificatesService = CertificatesService;
    this.UserService = UserService;
    this.LocalStorageService = LocalStorageService;
    this.page = 1;
    this.limit =  10;
    this.startPage = 1;
    this.endPage = 5;
  }

  findCertificates() {
    let object;
    let action = this.LocalStorageService.getItem('find_by');

    switch(action) {
      case 'all':
        object = {
          limit: this.limit,
          offset: this.limit * (this.page - 1)
        };
        break;
      case 'name':
        object = {
          name: this.name,
          limit: this.limit,
          offset: this.limit * (this.page - 1)
        };
        break;  
      case 'tag':
        object = {
          tags: this.tag,
          limit: this.limit,
          offset: this.limit * (this.page - 1)
        };
        break;
      case 'user':
        object = {
          id: this.LocalStorageService.getItem('userName'),
          limit: this.limit,
          offset: this.limit * (this.page - 1)
        }
        this.UserService.findCertificates(object).$promise.then((data) => {
          this.certificates = data.certificates;
          this.pages = data.pages;
          this.drawPagination();
        });
        return;   
    }

    this.CertificatesService.findAll(object).$promise.then((data) => {
      this.certificates = data.certificates;
      this.pages = data.pages;
      this.drawPagination();
    });
  }

  findAll() {
    this.LocalStorageService.addItem('find_by', 'all');
    this.findCertificates();
  }

  findByName() {
    this.LocalStorageService.addItem('find_by', 'name');
    this.findCertificates();
    this.name = null;
  }

  findByTag() {
    this.LocalStorageService.addItem('find_by', 'tag');
    this.findCertificates();
  }

  findUserCertificates() {
    this.LocalStorageService.addItem('find_by', 'user');
    this.findCertificates();
  }

  changeLanguage(lang) {
    this.LocalStorageService.addItem('locale', lang);
    this.$translate.use(this.LocalStorageService.getLocale());
  }

  remove(certificateId) {
    alertify.confirm(this.$filter('translate')('DELETE_MESSAGE'), () => {
      this.CertificatesService.remove({
        id: certificateId
      },
      () => {
        this.findCertificates();
        this.showSuccessMessage(this.$filter('translate')('DELETE_SUCCESS_CERTIFICATE'));
      }, 
      response => this.showErrorMessage(response.data.errorMessage));
    })
  }

  showSuccessMessage(successMessage) {
    iziToast.success({
      title: 'Success',
      titleSize: 22,
      message: successMessage,
      messageSize: 17,
      position: 'topLeft',
      timeout: 5000
    });
  }

  showErrorMessage(errorMessage) {
    iziToast.error({
      title: 'Error',
      titleSize: 22,
      message: errorMessage,
      messageSize: 17,
      position: 'topLeft',
      timeout: 5000
    });
  }

  addCertificate(certificate) {
    this.LocalStorageService.addItem('certificate', JSON.stringify(certificate));
  }

  buyCertificates() {
    this.PurchaseService.create({
      certificates: [
        {
          certificateId: this.certificate.id,
          count: 1
        }
      ]
    }, 
    () => {
      this.findCertificates();
      this.showSuccessMessage(this.$filter('translate')('PURCHASE_CERTIFICATE'));
    },
    response => this.showErrorMessage(response.data.errorMessage));
  }

  isFirstPage() {
    return this.page === 1;
  }

  isLastPage() {
    return this.page === this.pages;
  }

  previousPage() {
    --this.page;
    this.findCertificates();
  }

  nextPage() {
    ++this.page;
    this.findCertificates();
  }

  switchToFirstPage() {
    this.page = 1;
    this.startPage = 1;
    this.endPage = this.startPage + 5;
    this.findCertificates();    
  }

  switchToLastPage() {
    this.page = this.pages;
    this.endPage = this.pages;

    if (this.pages > 5) {
      this.startPage = this.endPage - 5;
    } else {
      this.startPage = this.endPage - this.pages;
    }

    this.findCertificates();
  }

  drawPagination() {
    this.arrayOfPages = [];

    if (this.page === this.endPage) {
      ++this.endPage;
      ++this.startPage;
    }

    if (this.page === this.startPage && this.page !== 1) {
      --this.endPage;
      --this.startPage;
    }

    for (let index = this.startPage; this.arrayOfPages.length < 5 && this.pages >= index; index++) {
      this.arrayOfPages.push(index);
    }
  }

  setLimit(newLimit) {
    this.limit = newLimit;
    this.findCertificates();
  }

  $onInit() {
    alertify.defaults.glossary.title = '';
    alertify.defaults.theme.ok = 'btn btn-light';
    alertify.defaults.theme.cancel = 'btn btn-primary';
    this.findAll();
  }

}
