import { NavLink } from 'react-router-dom';
import './styles.css';

const Navbar = () => {
    return (
        <nav className="base-card admin-nav-container">
            <ul className="base-card">
                <li>
                    <NavLink to="/dashboard/products" className="admin-nav-item">
                        <p>Produtos</p>
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/dashboard/categories" className="admin-nav-item">
                        <p>Categorias</p>
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/dashboard/users" className="admin-nav-item">
                        <p>Usuários</p>
                    </NavLink>
                </li>
            </ul>
        </nav>
    );
}

export default Navbar;