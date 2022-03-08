import { Route, Switch } from "react-router-dom";
import Form from "./Form";
import List from "./List";


const Products = () => {

    return (
        <div>
            <Switch>
                <Route path="/dashboard/products" exact>
                    <List />
                </Route>
                <Route path="/dashboard/products/:productId">
                    <Form />
                </Route>
            </Switch>
        </div>
    );
}

export default Products;