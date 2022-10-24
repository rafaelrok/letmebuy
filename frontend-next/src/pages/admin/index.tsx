import { useEffect, useState } from "react";
import { ProductsResponse } from "../../@types";
import { api } from "../../utils/api";
import { isAuthenticated } from "../../utils/auth";

import Login from "./auth";
import DashboardPage from "./dashboard/index";

export default function Admin({ products }: ProductsResponse) {
  const [logged, setLogged] = useState(false);

  useEffect(() => {
    if (localStorage.getItem("@dscatalog/token")) {
      setLogged(true);
    } else {
      setLogged(false);
    }
  }, []);

  if (logged) {
    return <DashboardPage products={products} />;
  } else {
    return <Login />;
  }
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
