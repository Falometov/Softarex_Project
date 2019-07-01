import { expect } from 'chai';
import Controller from './addcertificate.controller';

describe('addcertificate Controller', () => {
  it('Should be constructed', () => {
    // Arrange
    const controller = new Controller();

    // Act

    // Assert
    expect(controller).not.to.be.undefined;
  });
});
