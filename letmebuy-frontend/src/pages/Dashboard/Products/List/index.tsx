import { Link } from "react-router-dom";
import ProductCrudCard from "../ProductCrudCard";
import './styles.css';

const List = () => {

    const product = {
                "id": 2,
                "name": "Smart TV",
                "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "price": 2190.0,
                "imgUrl": "https://i.ibb.co/KXRj7dH/931303c53b5765fbc12a4a5c1e9004a0.png",
                "date": "2020-07-14T10:00:00Z",
                "categories": [
            {
                "id": 1,
                "name": "Livros"
            },
            {
                "id": 3,
                "name": "Computadores"
            }
        ]
    };

    return (
        <>
            <div className="product-crud-bar-container">
                <Link to="/dashboard/products/create">
                    <button className="btn btn-primary text-white btn-crud-add">ADICIONAR</button>
                </Link>
                <div className="base-card product-filter-container">Search bar</div>
            </div>
            <div className="row">
                <div className="col-sm-6 col-md-12">
                    <ProductCrudCard product={product} />
                </div>
                <div className="col-sm-6 col-md-12">
                    <ProductCrudCard product={product} />
                </div>
                <div className="col-sm-6 col-md-12">
                    <ProductCrudCard product={product} />
                </div>
            </div>
        </>
    );
}

export default List;