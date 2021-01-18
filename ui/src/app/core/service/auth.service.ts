import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {User} from '../model/user';
import {Observable, Subject} from 'rxjs';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private AUTH_URI = environment.baseURL + 'auth';
  private AUTH_URI_LOGIN = this.AUTH_URI + '/login';
  user: User = null;

  private userLoggedIn = new Subject<boolean>();

  constructor(
    private httpClient: HttpClient) {
    this.userLoggedIn.next(false);
  }

  setUserLoggedIn(userLoggedIn: boolean) {
    this.userLoggedIn.next(userLoggedIn);
  }

  getUserLoggedIn(): Observable<boolean> {
    return this.userLoggedIn.asObservable();
  }

  login(username: string, password: string) {
    return this.httpClient.post<any>(this.AUTH_URI_LOGIN, {username, password})
      .pipe(tap(res => {
          localStorage.setItem('user', JSON.stringify(res.user));
          this.user = res.user;
          this.setUserLoggedIn(true);
      }));
  }

  getUser() {
    if (!this.user) {
      this.user = Object.assign(new User(), JSON.parse(localStorage.getItem('user')) as User);
    }
    return this.user;
  }

  logout() {
    localStorage.removeItem('user');
    this.setUserLoggedIn(false);
  }

  isLoggedIn() {
    if (localStorage.getItem('user')) {
      return true;
    }
    return false;
  }

}
