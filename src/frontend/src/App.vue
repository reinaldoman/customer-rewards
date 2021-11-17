<template>
    <div id="app" class="container-fluid">
        <div class="site-info">
            <h1>Overactive</h1>
            <h3>Customer Rewards</h3>
        </div>
        <nav>
            <router-link class="btn btn-primary" to="/">Customers</router-link>
            <router-link class="btn btn-primary" to="/add">Add Customer</router-link>
            <router-link class="btn btn-primary" to="/add-purchases">Add Purchase</router-link>
            <!--router-link class="btn btn-primary" to="/search">Search</router-link-->
        </nav>
        <br/>
        <router-view/>
    </div>
</template>

<script>
export default {
  name: "app"
};
</script>

<style>
.site-info {
  color: blue;
  margin-bottom: 20px;
}

.btn-primary {
  margin-right: 5px;
}

.container-fluid {
  text-align: center;
}
</style>
