import { NavLink } from 'react-router-dom';
import './styles.css';

const Navbar = () => {
    return (
        <nav className="admin-nav-container">
            <ul>
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