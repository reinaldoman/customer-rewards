<template>
  <div v-if="this.customer">
    <h4>Customer</h4>
    <div>
      <label>Name: </label> {{this.customer.name}}
    </div>
   
    <span class="btn btn-primary" v-on:click="viewPoints()">Refresh Points</span>

    
    <h4>--------------</h4>
    <div v-if="showDetailsTable">
      <h4>Purchases</h4>
      <div>
        <template>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">
                    Value
                  </th>
                  <th class="text-left">
                    Description
                  </th>
                  <th class="text-left">
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
                  <td>{{ item.points }}</td>
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
      showDetailsTable: false,
    };
  },
  methods: {
    /* eslint-disable no-console */
    viewPoints() {
      this.showDetailsTable = true,
      this.purchases = [],
      this.loadPurchaseHistory();
    },
    loadPurchaseHistory() {
      http
        .get("/purchases/customer/" + this.customer.id)
        .then(response => {
          console.log(JSON.stringify(response));
          response.data.forEach((obj)=>{
              this.purchases.push({ value: obj.value, description: obj.description, points: obj.rewardedPoints })
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