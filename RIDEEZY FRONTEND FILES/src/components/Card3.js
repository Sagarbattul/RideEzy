import React, { useEffect, useState } from "react";
import axios from "axios";
import Carousel from "react-elastic-carousel";
// import img1 from "../assets/images/img1.png";
// import car from "../assets/images/car.jpg";
import { useNavigate } from "react-router-dom";
const breakPoints = [
  { width: 1, itemsToShow: 1 },
  { width: 550, itemsToShow: 3 },
  { width: 768, itemsToShow: 3 },
  { width: 1200, itemsToShow: 4 },
];

const Card3 = () => {
  const [news, setNews] = useState([]);
  // const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const { data } = JSON.parse(localStorage.getItem("data"));
  //const prarams = useParams();
  const navigate = useNavigate();
  const handleGetMore = (id) => {
    navigate({ pathname: `/show/${id}` });
  };
  useEffect(() => {
    axios
      .get(
        // "https://newsapi.org/v2/top-headlines?country=us&apiKey=4a9fe6d755b04c429b306f712f19ca58"
        `http://192.168.29.130:5000/api/vehicles/getVehiclesByCityandCategory/city/${data.city}/category/7`
      )
      .then((response) => {
        setNews(response.data);
        setLoading(true);
      })
      .catch((error) => {
        // setError(error.message);
        // setLoading(true);
        console.log(error);
      });
  }, []);

return(
      <>
      {loading ? (
        <div className="container-fluid">
          <div className="row">
          {news.data.length >= 1 ? (
            <Carousel breakPoints={breakPoints} showArrows={true}>
              {news.data.map((item, id) => {
                return (
                  <div className="col-12" key={item.id}>
                  <div
                  className="card text-center Hover"
                  style={{ width: "13rem", height: "22rem" }}
                >
                      <img
                        src={`http://192.168.29.130:5000/api/vehicles/vehicle/image/${item.vehicleImage}`}
                        style={{ width: "190px", height: "8rem" }}
                        className="card-img-top ms-2 mt-2"
                        alt="..."
                        showArrows={true}
                      />
                      <div className="card-body">
                       
                        <p className="card-title h6">{item.model}</p>
                        <span>
                        <p className="card-text text-danger">{item.city}</p>
                          </span>
                        <p className="card-text">Mileage:{item.mileage} km</p>
                        <p className="card-text h5">{item.fuelType} </p>
                        <p className="card-text text-success mar">**** (35)</p>

                        <button
                          onClick={() => {
                            handleGetMore(item.id);
                          }}
                          className="btn btn-primary mb-2 mar1 "
                        >
                          Get More
                        </button>
                      </div>
                    </div>
                  </div>
                );
              })}
            </Carousel>
            ) : (
              <h1 className="textblink text-danger">No Vehicles Available In Your City</h1>
            )}
          </div>
        </div>
        ) : (
          <div className="load-section">
            <div class="loader">
              <span></span>
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </div>
          </div>
        )}
      </>
    );
  
};
export default Card3;
