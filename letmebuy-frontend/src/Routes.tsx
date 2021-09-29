import Footer from "components/Footer";
import Navbar from "components/Navbar";
import Dashboard from "pages/Dashboard";

import Home from "pages/Home";
import Catalog from "pages/Catalog";

import { BrowserRouter, Redirect, Route, Switch } from "react-router-dom";
import ProductDetails from "pages/ProductDetails";


const Routes = () => (

    <BrowserRouter>
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
            <Redirect from="/dashboard" to="/dashboard/products" exact />
            <Route path="/dashboard">
                <Dashboard />
            </Route>
        </Switch>
        <Footer />
    </BrowserRouter>
);

export default Routes;