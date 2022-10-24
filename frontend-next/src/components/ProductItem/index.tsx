import Image from "next/image";
import Link from "next/link";
import styles from "./productitem.module.css";
import ProductPrice from "../ProductPrice";
import { ProductItemProps } from "../../@types";

export default function ProductItem(product: ProductItemProps) {
  const { imgUrl, name, price, id } = product;
  return (
    <Link href={`/catalog/product/${id}`}>
      <a className={`card-base border-radius-10 ${styles.productCard}`}>
        <div className={styles.cardTopContainer}>
          <Image
            src={imgUrl}
            alt={name}
            className={styles.productCardImage}
            width={197}
            height={158}
          />
        </div>
        <div className={styles.cardBottomContainer}>
          <h6>{name}</h6>
          <ProductPrice price={String(price)} />
        </div>
      </a>
    </Link>
  );
}
