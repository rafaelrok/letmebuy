import { GetServerSidePropsContext } from "next";
import Image from "next/image";
import Link from "next/link";
import { ProductPrice } from "../../../components";

import arrow from "../../../../public/blue_arrow.svg";

import { Product } from "../../../@types";
import { api } from "../../../utils/api";

import styles from "../../../styles/pages/product.module.css";

export default function ProductDetails({ productDetails }: Product) {
  const { imgUrl, name, price, description } = productDetails;
  return (
    <div className={styles.productDetailsContainer}>
      <div
        className={`d-flex flex-column card-base border-radius-20 ${styles.productDetails}`}
      >
        <Link href="/catalog">
          <a className={styles.productDetailsGoback}>
            <Image src={arrow} alt="Voltar" />
            <h1 className={styles.textGoBack}>voltar</h1>
          </a>
        </Link>
        <div className="row flex-column flex-lg-row align-items-center align-items-lg-start">
          <div className="col-xl-6">
            <div
              className={`text-center ${styles.productDetailsCard} ${styles.imgContainer}`}
            >
              <Image
                src={imgUrl}
                alt={name}
                className={styles.productDetailsImage}
                width={350}
                height={350}
              />
            </div>
            <div className="d-md-flex justify-content-md-between flex-md-row flex-lg-column">
              <h1 className={styles.productDetailsName}>{name}</h1>
              <ProductPrice price={String(price)} />
            </div>
          </div>
          <div className={`col-xl-6 ${styles.productDetailsCard}`}>
            <h1 className={styles.productDescriptionTitle}>
              Descrição do Produto
            </h1>
            <p className={styles.productDescriptionText}>{description}</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export async function getServerSideProps(ctx: GetServerSidePropsContext) {
  const { product: id } = ctx.query;
  const res = await api.get(`/products/${id}`);
  const productDetails = res.data;

  return {
    props: {
      productDetails,
    },
  };
}
