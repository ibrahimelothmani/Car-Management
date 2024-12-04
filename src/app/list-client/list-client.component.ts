import { Component } from '@angular/core';
import { Client } from '../Entity/client';
import { Router } from '@angular/router';
import { CrudService } from '../service/crudservice';

@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrl: './list-client.component.css',
})
export class ListClientComponent {
  role: String;
  listClient: Client[];

  constructor(private service: CrudService, private router: Router) {}

  //delete
  Deleteclient(client: Client) {
    if (
      confirm("you're going to delete the user with the ID " + client.id + ' ?')
    ) {
      this.service.deleteClient(client.id).subscribe(() => {
        this.router.navigate(['/client']).then(() => {
          window.location.reload();
        });
      });
    }
  }
  ngOnInit(): void {
    this.role = localStorage.getItem('role') as string;
    this.service.getClient().subscribe((client) => {
      this.listClient = client;
    });
  }
}
