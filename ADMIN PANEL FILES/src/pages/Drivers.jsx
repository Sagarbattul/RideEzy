import React from "react";
import "../styles/drivers.css";

import carData from "../assets/dummy-data/driver";
import CarItem from "../components/UI/CarItem";

const Drivers = () => {
    return (
        <div className="bookings">
          <div className="booking__wrapper">
            <h2 className="booking__title">Drivers</h2>
    
            <div className="filter__widget-wrapper">
              <div className="filter__widget-01">
                <select>
                  <option value="New">New</option>
                  <option value="Popular">Popular</option>
                  <option value="Upcoming">Upcoming</option>
                </select>
              </div>
    
              <div className="filter__widget-01">
                <select>
                  <option value="toyota">Toyota</option>
                  <option value="bmw">Bmw</option>
                  <option value="audi">Audi</option>
                </select>
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
}

export default Drivers;