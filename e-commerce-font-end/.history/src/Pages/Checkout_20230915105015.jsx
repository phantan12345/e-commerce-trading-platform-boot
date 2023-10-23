import { PayPalButtons, PayPalScriptProvider } from "@paypal/react-paypal-js";
import React, { useEffect } from "react";
import { Col, Container, Form, FormGroup, Row } from "react-bootstrap";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import Helmet from "../components/Helmet/Helmet";
import CommonSection from "../components/UI/CommonSection";
import "../styles/checkout.css";
import { endpoints } from "../configs/Apis";
import axios from "../configs/Apis";

const Checkout = () => {
  const [productData,set]

  useEffect(() => {
    const loadProducts = async () => {
      try {
        const response = await axios.get(endpoints['products']);
        setProductsData(response.data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };   loadProducts();
  }, []);



  const totalQuantity = useSelector((state) => state.cart.totalQuantity);
  const totalAmount = useSelector((state) => state.cart.totalAmount);

  return (
    <Helmet title="Checkout">
      <CommonSection title="Checkout" />
      <section>
        <Container>
          <Row>
            <Col lg="8">
              <h6 className="mb-4 fw-bold">Billing Information</h6>
              <Form>
                <FormGroup className="form__group">
                  <input type="text" placeholder="Enter your name: " />
                </FormGroup>
                <FormGroup className="form__group">
                  <input type="email" placeholder="Enter your email: " />
                </FormGroup>
                <FormGroup className="form__group">
                  <input type="number" placeholder="Phone number: " />
                </FormGroup>
                <FormGroup className="form__group">
                  <input type="text" placeholder="Street address " />
                </FormGroup>
                <FormGroup className="form__group">
                  <input type="text" placeholder="City " />
                </FormGroup>
                <FormGroup className="form__group">
                  <input type="text" placeholder="Postal code " />
                </FormGroup>
                <FormGroup className="form__group">
                  <input type="text" placeholder="Country " />
                </FormGroup>
              </Form>
            </Col>
            <Col lg="4">
              <div className="checkout__cart">
                <h6>
                  Total Quantity: <span>{totalQuantity} items</span>{" "}
                </h6>
                <h6>
                  Subtotal: <span>${totalAmount}</span>{" "}
                </h6>
                <h6>
                  Shipping: <br />
                  Free shipping <span>$1</span>{" "}
                </h6>
                <h4>
                  Total Cost: <span>${totalAmount}</span>
                </h4>
                <button className="buy__btn auth__btn w-100">
                  <Link to="/login">Place an order</Link>
                </button>
                <PayPalScriptProvider options={{ clientId: "test" }}>
                  <PayPalButtons className="mt-2" style={{}} />
                </PayPalScriptProvider>
              </div>
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Checkout;
