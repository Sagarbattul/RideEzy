import React from "react";
import carData from "../assets/dummy-data/vehical";
import CarItem from "../components/UI/CarItem.jsx";

const Vehicals = () => {
  return (
    <>
      <div className="bookings">
        <div className="booking__wrapper">
          <h2 className="booking__title">Vehicals</h2>
          <div className="booking__car-list">
            {carData?.map((item) => (
              <CarItem item={item} key={item.id} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
};

export default Vehicals;
