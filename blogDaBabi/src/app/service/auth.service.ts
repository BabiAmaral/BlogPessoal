import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { UserLogin } from '../model/UserLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( 
    private http: HttpClient
    ) { }

  entrar(userLogin: UserLogin): Observable<UserLogin>{
    return this.http.post<UserLogin>('https://babiblogpessoal.herokuapp.com/usuario/logar', userLogin)
  }

  cadastrar(user: User) : Observable<User>{
    return this.http.post<User>('https://babiblogpessoal.herokuapp.com/usuario/cadastrar', user)
  }

}
