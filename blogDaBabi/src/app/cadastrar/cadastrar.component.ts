import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { AuthService } from '../service/auth.service';


@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  user: User = new User
  confirmarSenha: string
  tipoUsuario: string

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0,0)
  }

  pegarSenha(event: any){
    this.confirmarSenha = event.target.value
    console.log("evento senha "+  event.target.value)

  }

  tipoUser(event: any){
    this.tipoUsuario = event.target.value
    
  }

  cadastrar(){
    this.user.tipo = this.tipoUsuario
    console.log("senha do usuario "+ this.user.senha)
    console.log("confirmar senha "+ this.confirmarSenha )
    if(this.user.senha != this.confirmarSenha){
      alert('As senhas estão incorretas.')
    } else{
      
      this.authService.cadastrar(this.user).subscribe((resp: User) => {
        this.user = resp
        this.router.navigate(['/entrar'])
        alert('Usuário cadastrado com sucesso!')
      })
    }
  }

}
