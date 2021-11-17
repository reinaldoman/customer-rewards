<template>
  <div v-if="this.customer">
    <h4>Customer</h4>
    <div>
      <label>Name: </label> {{this.customer.name}}
    </div>
   
    <span class="btn btn-primary" v-on:click="viewPoints()">Refresh Points</span>

    <h6 style="color: white;">--------------------</h6>
    
    <span class="btn btn-primary" v-on:click="viewPointsPerMonth()">Points per month</span>

    
    <h6 style="color: white;">--------------</h6>
    <div v-if="showDetailsTable">
      <h4>Purchases</h4>
      <div>
        <template>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th style="padding: 15px; text-align: center;">
                    Value
                  </th>
                  <th style="padding: 15px; text-align: center;">
                    Description
                  </th>
                  <th style="padding: 30px; text-align: center;">
                    Date
                  </th>
                  <th style="padding: 15px; text-align: center;">
                    Points
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="item in purchases"
                  :key="item.description"
                >
                  <td>{{ item.value }}</td>
                  <td>{{ item.description }}</td>
                  <td>{{ item.date }}</td>
                  <td>{{ item.points }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </template>
      </div>
    </div>
    <div v-if="showTotalPointsTable">
      <h4>Points per month</h4>
      <div>
        <template>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th style="padding: 15px; text-align: center;">
                    Month
                  </th>
                  <th style="padding: 15px; text-align: center;">
                    Points
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="item in monthlyPoints"
                  :key="item.month"
                >
                  <td style="text-align: center;">{{ item.month }}</td>
                  <td style="text-align: center;">{{ item.points }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </template>
      </div>
    </div>
  </div>
  <div v-else>
    <br/>
    <p>Please click on a Customer...</p>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "customer",
  props: ["customer"],
  data() {
    return {
      purchases: [],
      monthlyPoints: [],
      showDetailsTable: false,
      showTotalPointsTable: false,
    };
  },
  methods: {
    /* eslint-disable no-console */
    viewPoints() {
      this.showDetailsTable = true,
      this.showTotalPointsTable = false,
      this.purchases = [],
      this.loadPurchaseHistory();
    },
    viewPointsPerMonth() {
      this.showDetailsTable = false,
      this.showTotalPointsTable = true,
      this.monthlyPoints = [],
      this.loadPointsPerMonth();
    },
    loadPurchaseHistory() {
      http
        .get("/purchases/customer/" + this.customer.id)
        .then(response => {
          response.data.forEach((obj)=>{
              let splittedMonth = obj.date.split("-")
              if(splittedMonth[1] < 9)
                splittedMonth[1] = (parseInt(splittedMonth[1]) + 1)
              this.purchases.push({ value: obj.value, description: obj.description, date: splittedMonth[0] + '-' + splittedMonth[1] + '-' + splittedMonth[2], points: obj.rewardedPoints })
          })
        })
        .catch(e => {
          console.log(e);
        });
    },
    loadPointsPerMonth() {
      http
        .get("/purchases/customer/points/" + this.customer.id)
        .then(response => {
          response.data.forEach((obj)=>{
            this.monthlyPoints.push({month: obj[0] < 9 ? (obj[0] + 1) : obj[0], points: obj[1]})
          })
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  },
};
</script>