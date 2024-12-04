import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../Entity/admin';
import { CrudService } from '../service/crudservice';

@Component({
  selector: 'app-list-admin',
  templateUrl: './list-admin.component.html',
  styleUrl: './list-admin.component.css'
})
export class ListAdminComponent {
  role:String
  listAdmin: Admin[];
 
  constructor(private service:CrudService,private router:Router ) { }


  //supprimer
  Deleteadmin(admin: Admin){
    if(confirm("Voulez vous supprimer cet admin avec l'ID " + admin.id + " ?")) {
     
      this.service.deleteAdmin(admin.id).subscribe(() => {
        this.router.navigate(['/admin']).then(() => {
          window.location.reload()
        })
      })
   
  }
}
Modifyadmin(admin: Admin){
  this.router.navigate(['/modify', admin]);
  }


  ngOnInit(): void {
    this.role=localStorage.getItem("role")as string;
    this.service.getAdmin().subscribe(admin => {
      this.listAdmin = admin
    })
  }
}
