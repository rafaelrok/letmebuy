import Link from "next/link";
import Image from "next/image";
import styles from "./productitemadmin.module.css";

import ProductPrice from "../ProductPrice";
import { ProductItemProps } from "../../@types";

export default function ProductItemAdmin(product: ProductItemProps) {
  const { imgUrl, name, price, id, categories } = product;
  return (
    <div className={`card-base ${styles.productCardAdmin} container-fluid`}>
      <div className="d-flex flex-column flex-md-row align-items-center p-4">
        <div className="col-lg-2 text-center border-lg-end p-3">
          <Image
            src={imgUrl}
            alt="Produto"
            className={styles.productCardImageAdmin}
            width={100}
            height={100}
          />
        </div>
        <div className="col-md-6 col-12 p-md-3 ">
          <h3 className={styles.productCardNameAdmin}>{name}</h3>
          <ProductPrice price={String(price)} />
          <div>
            <span className="badge rounded-pill bg-secondary text-dark">
              {categories[0]}
            </span>
            <span className="badge rounded-pill bg-secondary text-dark mx-2">
              {categories[1]}
            </span>
          </div>
        </div>
        <div className="col-12 col-md-4 pt-3 d-flex flex-row-reverse flex-md-column justify-content-between align-items-md-end gap-2">
          <Link href={`/admin/products/1231231`}>
            <a className="btn  btn-outline-secondary btn-block border-radius-10 col-6">
              EDITAR
            </a>
          </Link>
          <button
            type="button"
            className="btn btn-outline-danger btn-block border-radius-10 col-6 "
          >
            EXCLUIR
          </button>
        </div>
      </div>
    </div>
  );
}
