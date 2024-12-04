import { Component } from '@angular/core';
import { CrudService } from '../service/crudservice';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  userDetails: any;
  totalAdmin: number = 0;
  totalCandidat: number = 0;
  totalContact: number = 0;
  totalOffre: number = 0;
  chart: any = [];

  myGroup: FormGroup; // Define FormGroup

  constructor(private router: Router, private service: CrudService) {

    // this.userDetails = this.service.userDetails();
    this.myGroup = new FormGroup({
      firstName: new FormControl(), // Initialize FormControl
    });
    Chart.register(...registerables); // Register Chart.js components
  }

  ngOnInit(): void {
    this.service.getAdmin().subscribe(admin => {
      this.totalAdmin = admin.length
    });
    this.service.getClient().subscribe(client => {
      this.totalCandidat = client.length
    });
    this.service.getContact().subscribe(contact => {
      this.totalContact = contact.length
    });
    // Fetch data and initialize charts
  }

  updatePercentageChart() {
    const totalUsers = this.totalCandidat + this.totalContact + this.totalOffre;
    const percentageCandidat = (this.totalCandidat / totalUsers) * 100;
    const percentageContact = (this.totalContact / totalUsers) * 100;
    const percentageOffres = (this.totalOffre / totalUsers) * 100;
    const percentageChart = new Chart('percentageCanvas', {
      type: 'pie',
      data: {
        labels: ['Clients', 'Coachs', 'Salles de sport'],
        datasets: [
          {
            label: 'Pourcentage des utilisateurs',
            data: [percentageCandidat, percentageContact, percentageOffres],
            backgroundColor: ['#ff8a65', '#4bc0c0', '#8B65D2'],
            borderColor: ['#ff8a65', '#4bc0c0', '#8B65D2'],
            borderWidth: 2,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            position: 'bottom',
            labels: {
              font: {
                family: 'Arial',
                size: 14,
              },
            },
          },
          title: {
            display: true,
            text: 'Pourcentage des utilisateurs sur la plateforme',
            font: {
              family: 'Arial',
              size: 18,
              weight: 'bold',
            },
          },
        },
        layout: {
          padding: {
            left: 20,
            right: 20,
            top: 20,
            bottom: 20,
          },
        },
      },
    });
  }

}
