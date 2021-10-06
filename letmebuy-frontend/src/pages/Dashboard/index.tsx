import PrivateRoute from "components/PrivateRoute";
import { Switch } from "react-router";
import Navbar from "./Navbar";
import Users from "./Users";

import './styles.css';
import Products from "./Products";
import Categories from "./Categories";


const Dashboard = () => {

    return (
        <div className="admin-container">
            <Navbar />
            <div className="admin-content">
                <Switch>
                    <PrivateRoute path="/dashboard/products" roles={["ROLE_OPERATOR"]}>
                        <Products />
                    </PrivateRoute>
                    <PrivateRoute path="/dashboard/categories" roles={["ROLE_OPERATOR"]}>
                        <Categories />
                    </PrivateRoute>
                    <PrivateRoute path="/dashboard/users" roles={["ROLE_OPERATOR"]}>
                        <Users />
                    </PrivateRoute>
                </Switch>
            </div>

        </div>
    );
}

export default Dashboard;