import { useRouter } from "next/router";
import { ProductsResponse } from "../../../@types";
import { Sidebar } from "../../../components";
import { api } from "../../../utils/api";
import { Products, Users, Categories } from "./pages";

export default function DashboardPage({ products }: ProductsResponse) {
  const route = useRouter();

  const { index } = route.query;

  const returnRoute = () => {
    switch (index) {
      case "products":
        return <Products products={products} />;

      case "users":
        return <Users />;

      case "categories":
        return <Categories />;

      default:
        return <Products products={products} />;
    }
  };

  return (
    <div className="d-flex flex-column flex-lg-row">
      <Sidebar />
      {returnRoute()}
    </div>
  );
}
