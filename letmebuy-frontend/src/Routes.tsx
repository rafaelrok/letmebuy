import Footer from "components/Footer";
import Navbar from "components/Navbar";
import Dashboard from "pages/Dashboard";

import Home from "pages/Home";
import Catalog from "pages/Catalog";

import { Router, Redirect, Route, Switch } from "react-router-dom";
import ProductDetails from "pages/ProductDetails";
import Auth from "pages/Dashboard/Auth";
import history from "util/history";


const Routes = () => (

    <Router history={history}>
        <Navbar />
        <Switch>
            <Route path="/" exact>
                <Home />
            </Route>
            <Route path="/products" exact>
                <Catalog />
            </Route>
            <Route path="/products/:productId">
                <ProductDetails />
            </Route>
            <Redirect from="/dashboard/auth" to="/dashboard/auth/login" exact />
            <Route path="/dashboard/auth">
                <Auth />
            </Route>
            <Redirect from="/dashboard" to="/dashboard/products" exact />
            <Route path="/dashboard">
                <Dashboard />
            </Route>
        </Switch>
        <Footer />
    </Router>
);

export default Routes;