import React from "react";
import "../styles/bookings.css";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import carData from "../assets/dummy-data/booking-cars.js";
import CarItem from "../components/UI/CarItem";

const Bookings = () => {
  return (
    <div className="bookings">
      <div className="booking__wrapper">
        <h2 className="booking__title">Booking</h2>
        <div className="d-flex align-items-center justify-content-between mb-5">
          <div>
            <label>Customer</label>
            <Form.Select aria-label="Default select example">
              <option>Open this select menu</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </Form.Select>
          </div>
          <div>
            <label>vehicle</label>

            <Form.Select aria-label="Default select example">
              <option>Open this select menu</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </Form.Select>
          </div>
          <div>
            <label>Driver</label>
            <Form.Select aria-label="Default select example">
              <option>Open this select menu</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </Form.Select>
          </div>
          <div>
            <label>Amount</label>

            <Form.Select aria-label="Default select example">
              <option>Open this select menu</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </Form.Select>
          </div>
        </div>
        <div className="booking__car-list">
          {carData?.map((item) => (
            <CarItem item={item} key={item.id} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Bookings;
