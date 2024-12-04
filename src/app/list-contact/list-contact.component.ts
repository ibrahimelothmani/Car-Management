import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Contact } from '../Entity/contact';
import { CrudService } from '../service/crudservice';


@Component({
  selector: 'app-list-contact',
  templateUrl: './list-contact.component.html',
  styleUrls: ['./list-contact.component.css']
})
export class ListContactComponent {

  role:String
  listContact: Contact[];
 
  constructor(private service:CrudService,private router:Router ) { }


  //supprimer
  Deletecontact(contact: Contact){
    if(confirm("Voulez vous supprimer cet admin avec l'ID " + contact.id + " ?")) {
     
      this.service.deleteContact(contact.id).subscribe(() => {
        this.router.navigate(['/contact']).then(() => {
          window.location.reload()
        })
      })
   
  }
}
  ngOnInit(): void {
    this.role=localStorage.getItem("role")as string;
    this.service.getContact().subscribe(contact => {
      this.listContact = contact
    })
  }
}