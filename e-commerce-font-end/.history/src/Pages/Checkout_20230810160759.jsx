import React from 'react';
import { Col, Container, Form, FormGroup, Row } from 'react-bootstrap';
import Helmet from '../components/Helmet/Helmet';
import CommonSection from '../components/UI/CommonSection';
import '../styles/checkout.css';

const Checkout = () => {
    return <Helmet title ='Checkout'>
        <CommonSection title='Checkout'/>
        <section>
            <Container>
                <Row>
                    <Col lg='8'>
                        <h6 className="mb-4 fw-bold">Billing Information</h6>
                        <Form>
                            <FormGroup className='form__group'>
                                <input type="text" placeholder='Enter your name: ' />
                            </FormGroup>
                            <FormGroup className='form__group'>
                                <input type="email" placeholder='Enter your email: ' />
                            </FormGroup>
                            <FormGroup className='form__group'>
                                <input type="number" placeholder='Phone number: ' />
                            </FormGroup>
                            <FormGroup className='form__group'>
                                <input type="text" placeholder='Street address ' />
                            </FormGroup>
                            <FormGroup className='form__group'>
                                <input type="text" placeholder='City ' />
                            </FormGroup>
                            <FormGroup className='form__group'>
                                <input type="text" placeholder='Postal code ' />
                            </FormGroup>
                            <FormGroup className='form__group'>
                                <input type="text" placeholder='Country ' />
                            </FormGroup>
                        </Form>
                    </Col>
                    <Col lg='4'>
                        <div className="checkout__cart">
                            <h6>Total Quantity: <span>0</span> </h6>
                            <h6>Subtotal: <span>$111</span> </h6>
                            <h6>Shipping: <span>$111</span> </h6>
                        </div>
                    </Col>
                </Row>
            </Container>
        </section>
    </Helmet>
}

export default Checkout;
