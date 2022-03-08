import ProductCard from 'components/ProductCard';
import { Product } from 'types/product';
import { Link } from 'react-router-dom';
import Pagination from 'components/Pagination';
import {useState, useEffect, useCallback} from 'react';
import { SpringPage } from 'types/vendor/spring';
import { requestBackend } from 'util/requests';
import { AxiosRequestConfig } from 'axios';
import CardLoader from './CardLoader';

import './styles.css';
import ProductFilter, {ProductFilterData} from "../../components/ProductFilter";


type ControlComponentsData = {
    activePage: number;
    filterData: ProductFilterData;
};

const Catalog = () => {
    const [page, setPage] = useState<SpringPage<Product>>();
    const [isLoading, setIsLoading] = useState(false);

    const [controlComponentsData, setControlComponentsData] =
        useState<ControlComponentsData>({
            activePage: 0,
            filterData: { name: '', category: null },
        });

    const handlePageChange = (pageNumber: number) => {
        setControlComponentsData({ activePage: pageNumber, filterData: controlComponentsData.filterData });
    };

    const handleSubmitFilter = (data: ProductFilterData) => {
        setControlComponentsData({ activePage: 0, filterData: data });
    };

    const getProducts = useCallback( () => {
        const params: AxiosRequestConfig = {
            method: 'GET',
            url: '/products',
            params: {
                page: controlComponentsData.activePage,
                size: 12,
                name: controlComponentsData.filterData.name,
                categoryId: controlComponentsData.filterData.category?.id
            },
        };

        setIsLoading(true);
        requestBackend(params)
            .then((response) => {
                setPage(response.data);
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, [controlComponentsData]);

    useEffect(() => {
        getProducts();
    }, [getProducts]);

    return (
        <div className="container my-4 catalog-container">
            <div className="row catalog-title-container">
                <h1>Catálogo de produtos</h1>
            </div>
            <div className="catalog-filter-container">
                <ProductFilter onSubmitFilter={handleSubmitFilter} />
            </div>

            <div className="row">
                {isLoading ? (
                    <CardLoader />
                ) : (
                    page?.content.map((product) => (
                        <div className="col-sm-6 col-lg-4 col-xl-3" key={product.id}>
                            <Link to={`/products/${product.id}`}>
                                <ProductCard product={product} />
                            </Link>
                        </div>
                    ))
                )}
            </div>

            <div className="row">
                <Pagination
                    forcePage={page?.number}
                    pageCount={page ? page.totalPages : 0}
                    range={3}
                    onChange={handlePageChange}
                />
            </div>
        </div>
    );
};

export default Catalog;
