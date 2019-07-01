import { expect } from 'chai';
import Service from './purchase.service';

describe('purchase Service', () => {
  it('Should be constructed', () => {
    // Arrange
    const service = new Service();

    // Act

    // Assert
    expect(service).not.to.be.undefined;
  });
});
