import { ProductItem } from "../../components";
import { api } from "../../utils/api";
import { ProductsResponse } from "../../@types";
import styles from "../../styles/pages/catalog.module.css";

export default function ProductsPage({ products }: ProductsResponse) {
  return (
    <div className="container">
      <h3 className="my-4">Catálogo de Produtos</h3>
      <div className={styles.catalogProducts}>
        {products.map((product, index) => (
          <ProductItem key={index} {...product} />
        ))}
      </div>
    </div>
  );
}

export async function getServerSideProps() {
  const params = {
    page: 0,
    size: 12,
    sort: "name,asc",
  };
  const res = await api({ url: "/products", params });
  const products = res.data.content;

  return {
    props: {
      products,
    },
  };
}
