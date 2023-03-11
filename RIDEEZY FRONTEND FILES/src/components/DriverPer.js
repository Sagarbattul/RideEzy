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

const Driverper = ({city}) => {
  const [news, setNews] = useState([]);
  // const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const { data } = JSON.parse(localStorage.getItem("data"));
  //const prarams = useParams();
  const navigate = useNavigate();
  const handleGetMore = (d_id) => {
    navigate({ pathname: `/drivershowpage/${d_id}` });
  };
  console.log("dirver city",city)
  useEffect(() => {
    axios.get(
        `http://192.168.29.130:5000/api/drivers/getDriversByCityAndCategory/city/${city.length==1?data.city:city}/category/4`)
      .then((response) => {
        setNews(response.data);
        setLoading(true);
      })
      .catch((error) => {
        console.log(error);       
      });
  }, [city]);

    return (
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
                        src={`http://192.168.29.130:5000/api/vehicles/vehicle/image/${item.driverImage}`}
                        style={{ width: "190px", height: "8rem" }}
                        className="card-img-top ms-2 mt-2"
                        alt="..."
                        showArrows={true}
                      />
                      <div className="card-body">
                        {/* <h5 className="card-title">
                          Hennessey{" "}
                          <span>
                            <h6 className="text-danger">Venom GT</h6>
                          </span>
                        </h5> */}
                        <h5 className="card-title h6">
                          {item.d_firstName} {item.d_lastName} {" "}
                          <span>
                        <p className="card-text text-danger">{item.d_city}</p>
                          </span>
                        </h5>
                            <h6 className="text">{item.d_altMobNo}</h6>
                        <p className="card-text">Exp.:{item.d_ridingExperience} (Yrs.)</p>
                        <p className="card-text text-success ms-5">***** (35)</p>
                        <button
                          onClick={() => {
                            handleGetMore(item.d_id);
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
              <h1 className="textblink text-danger">No Drivers Available In Your City</h1>
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
export default Driverper;
