import { AppPage } from './app.po';
import { browser, by, element, logging } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should contain <router-outlet>', () => {
    page.navigateTo();
    expect(page.getRouterOutlet).toBeTruthy('<router-outlet> should exist in app.component.html');
  });

  it('should load homepage component on base url', () => {
    browser.get('/');
    element(by.linkText('Blissful Bites')).click();
    expect<any>(browser.getCurrentUrl())
      .toEqual(browser.baseUrl+'homepage');
  });

  it('should load homepage component view on clicking blissful bites and verify the url', () => {
    browser.get('/homepage');
    element(by.linkText('Blissful Bites')).click();
    expect<any>(browser.getCurrentUrl())
      .toEqual(browser.baseUrl + 'homepage');
  });

  it('should display the title', ()=>{
      page.navigateTo();
      expect<any>(page.getTitle()).toEqual('Blissful Bites')
  });

  it('should load login page on clicking login button and verify the url' , ()=>{
    page.navigateTo();
    element(by.css('mattooltip="Login"')).click();
    expect<any>(browser.getCurrentUrl())
      .toEqual(browser.baseUrl + 'login');
  });
  
  it('should load register page on clicking of register button and verify url' , ()=>{
    page.navigateTo();
    element(by.css('mattooltip="Login"')).click();
    element(by.css('mattooltip="SignUp"')).click();
    expect<any>(browser.getCurrentUrl())
      .toEqual(browser.baseUrl + 'register');
  })

  it('should navigate to login page if not loggied in and click on add favorites' , ()=>{
      page.navigateTo();
      element(by.css('class="fa fa-heart-o"')).click();
      expect<any>(browser.getCurrentUrl())
        .toEqual(browser.baseUrl +'login');
  });

  it('should navigate to login page on visiting the favorite page as its router guard' , ()=>{
    browser.get('/favorite');
    expect<any>(browser.getCurrentUrl())
    .toEqual(browser.baseUrl +'login');
  })
  
  it('should register then navigate to login page  when credentials are given' , ()=>{
    browser.get('/register');
    page.getEmailText().sendKeys('Username@gmail.com');
    page.getPasswordText().sendKeys('12345678');
    page.getConformPassword().sendKeys('12345678');
    page.getSignupButton().click();
    expect<any>(browser.getCurrentUrl())
      .toEqual(browser.baseUrl+'login');
  });

  it('should retain in login page when logged in not-successful' , ()=>{
    browser.get('/login');
    page.getEmailText().sendKeys('');
    page.getPasswordText().sendKeys('');
    page.getSignupButton().click();
    expect<any>(browser.getCurrentUrl())
      .toEqual(browser.baseUrl+'login');
  })

  it('should navigate to the favorite page when logged in successfully' , ()=>{
    browser.get('/login');
    page.getEmailText().sendKeys('Username@gmail.com');
    page.getPasswordText().sendKeys('12345678');
    page.getSignupButton().click();
    expect<any>(browser.getCurrentUrl())
      .toEqual(browser.baseUrl+'favorite');
  })

  it('should store email in local storage when logged in successfully' , ()=>{
    browser.get('/login');
    page.getEmailText().sendKeys('Username@gmail.com');
    page.getPasswordText().sendKeys('12345678');
    page.getSignupButton().click();
    let valueLocalStorage= browser.executeScript("return window.localStorage.getItem('email');");
    expect<any>(valueLocalStorage).toEqual('Username@gmail.com');
  })

});
