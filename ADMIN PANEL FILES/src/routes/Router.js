import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import Dashboard from "../pages/Dashboard";
import Bookings from "../pages/Bookings";
import Vehicals from "../pages/Vehicals";
import Settings from "../pages/Settings";
import Drivers from  "../pages/Drivers";
import Login from "../pages/Login";
// import Driverss from "../pages/Drivers";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/bookings" element={<Bookings />} />
      <Route path="/drivers" element={<Drivers />} />
      <Route path="/settings" element={<Settings />} />
      <Route path="/vehicals" element={<Vehicals />} />
      {/* <Route path="/drivers" element={<Drivers/>} /> */}
    </Routes>
  );
};

export default Router;
