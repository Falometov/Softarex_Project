import { expect } from 'chai';
import Controller from './editcertificate.controller';

describe('editcertificate Controller', () => {
  it('Should be constructed', () => {
    // Arrange
    const controller = new Controller();

    // Act

    // Assert
    expect(controller).not.to.be.undefined;
  });
});
