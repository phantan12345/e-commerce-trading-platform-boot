import React, { useEffect, useState } from "react";
import { ProgressBar } from "react-loader-spinner";
import "./App.css";
import Layout from "./components/Layout/Layout";

function App() {
  const [firstLoad, setFirstLoad] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setFirstLoad(true)
    }, 3000)
  }, []);
  return (
    <>
      {firstLoad ? (
        <Layout />
      ) : (
        <PacmanLoaderLoader color="#ad36d6" />
      )}
    </>
  );
}

export default App;