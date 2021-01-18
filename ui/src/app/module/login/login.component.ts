import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../core/service/auth.service';
import {User} from '../../core/model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  form: FormGroup;
  errors = [];

  constructor(
    public fb: FormBuilder,
    public router: Router,
    public authService: AuthService) {

    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {

    this.errors = [];

    const user: User = {
      firstName:'',
      lastName: ''
    }
    localStorage.setItem('user', JSON.stringify(user));
    this.authService.setUserLoggedIn(true);
    this.router.navigate(['/visit']);

  //   const val = this.form.value;

  //   if (val.email === '') {
  //     alert('Email is required');
  //   }

  //   if (val.password === '') {
  //     alert('Password is required');
  //   }

  //   if (val.email && val.password) {

  //     this.authService.login(val.email, val.password)
  //       .subscribe(
  //         (res) => {
  //             const user = Object.assign(new User(), JSON.parse(localStorage.getItem('user')) as User);
  //             this.router.navigate(['/appointment']);
  //         },
  //         (error) => {
  //           this.errors = error.error.errors;
  //         });
  //   }

  }

}
