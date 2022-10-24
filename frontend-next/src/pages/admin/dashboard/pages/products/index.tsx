import Link from "next/link";
import styles from "../../../../../styles/pages/dashboardProducts.module.css";
import { ProductItemAdmin } from "../../../../../components";
import { ProductsResponse } from "../../../../../@types";
import { api } from "../../../../../utils/api";

export default function ProductsAdmin({ products }: ProductsResponse) {
  return (
    <div className={styles.productListContainer}>
      <Link href="/">
        <a className={`btn btn-primary ${styles.add}`}>Adicionar</a>
      </Link>
      <div className="d-flex flex-column container-fluid">
        {products.map((product, index) => (
          <ProductItemAdmin key={index} {...product} />
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
