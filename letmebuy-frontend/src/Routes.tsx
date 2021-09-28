import Footer from "components/Footer";
import Navbar from "components/Navbar";
import Dashboard from "pages/Dashboard";

import Home from "pages/Home";
import Catalog from "pages/Catalog";

import { BrowserRouter, Route, Switch } from "react-router-dom";


const Routes = () => (

    <BrowserRouter>
        <Navbar />
        <Switch>
            <Route path="/" exact>
                <Home />
            </Route>
            <Route path="/products">
                <Catalog />
            </Route>
            <Route path="/dashboard">
                <Dashboard />
            </Route>
        </Switch>
        <Footer />
    </BrowserRouter>
);

export default Routes;