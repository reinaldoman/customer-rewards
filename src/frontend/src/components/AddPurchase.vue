<template>
  <div class="submitform">
    <div v-if="!submitted">

        <div class="form-group">
            <label for="customer">Customer</label>
            <select class="form-control" v-model="selectedCustomer" @change="setSelectedCustomer(selectedCustomer)">
                <option v-for="customer in customers" v-bind:value="customer.value" v-bind:key="customer.value">
                    {{ customer.text }}
                </option>
            </select>
        </div>
        
        <div class="form-group">
          <label for="description">Description</label>
          <input type="text" class="form-control" id="description" required v-model="purchase.description" name="description">
        </div>

        <div class="form-group">
          <label for="value">Value</label>
          <input type="number" class="form-control" id="value" required v-model="purchase.value" name="value">
        </div>

        <div class="form-group">
          <label for="date">Date</label>
          <input type="date" class="form-control" id="date" required v-model="selectedDate" name="date" @change="setSelectedDate(selectedDate)">
        </div>
    
        <button v-on:click="savePurchase" class="btn btn-success">Submit</button>
    </div>
    
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" v-on:click="newPurchase">Add</button>
    </div>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "add-purchase",
  data() {
    return {
      purchase: {
        id: 0,
        description: "",
        value: 0,
        date: null
      },
      customers: [{text: '', value: 0}],
      selectedCustomer: 0,
      selectedDate: null,
      submitted: false
    };
  },
  methods: {
    /* eslint-disable no-console */
    savePurchase() {
      console.log('Before saving ' + this.selectedCustomer)
      var data = {
        description: this.purchase.description,
        value: this.purchase.value,
        customerId: this.selectedCustomer,
        date: this.selectedDate
      };

      http
        .post("/purchase", data)
        .then(response => {
          this.purchase.id = response.data.id;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

      this.submitted = true;
    },
    newPurchase() {
      this.submitted = false;
      this.purchase = {};
    },
    loadCustomers() {
        console.log('Loading customers...')
        http
        .get("/customers")
        .then(response => {
          console.log(JSON.stringify(response));
          response.data.forEach((obj)=>{
              this.customers.push({ text: obj.name, value: obj.id })
          })
          
        })
        .catch(e => {
          console.log(e);
        });
        console.log('Customers loaded...')
    },

    setSelectedCustomer(selectedCustomer) {
        this.selectedCustomer = selectedCustomer
    },

    setSelectedDate(setSelectedDate) {
        this.setSelectedDate = setSelectedDate
    }

  },
  mounted() {
      this.loadCustomers();
  }
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
