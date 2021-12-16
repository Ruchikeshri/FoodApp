import { browser, by, element, ElementFinder } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl);
  }

  getTitle(){
    return element(by.css('href="/"')).getText();
  }

  getRouterOutlet(): ElementFinder {
    return element(by.tagName('router-outlet'));
  }
  
  getEmailText(){
    return element(by.id('email'));
  }

  getPasswordText(){
    return element(by.id('password'));
  }

  getConformPassword(){
    return element(by.id('Confirm Password'));
  }

  getSignupButton(){
    return element(by.css('type="submit"'));
  }
}
