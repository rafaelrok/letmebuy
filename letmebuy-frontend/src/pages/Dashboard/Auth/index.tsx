
import { Route, Switch } from 'react-router-dom';
import { ReactComponent as AuthImage } from '../../../assets/images/auth-image.svg';

import './styles.css';

const Auth = () => {


    return (
        <div className="auth-container">
            <div className="auth-banner-container">
                <h1>
                    Divulgue seu produtos no lemebuy
                </h1>
                <p>
                    Faça parte do nosso catálogo de divulgação e aumente a venda dos seus produtos.
                </p>
                <AuthImage />
            </div>
            <div className="auth-form-container">
                <Switch>
                    <Route path="/dashboard/auth/login">
                        <h1>Card login</h1>
                    </Route>
                    <Route path="/dashboard/auth/signup">
                        <h1>Card signup</h1>
                    </Route>
                    <Route path="/dashboard/auth/recover">
                        <h1>Card recover</h1>
                    </Route>
                </Switch>
            </div>
        </div>
    );
}

export default Auth;