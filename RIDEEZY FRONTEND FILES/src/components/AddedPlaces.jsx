
import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useParams } from "react-router-dom";
import "./addedplace.css";
import Carousel from "react-elastic-carousel";

import { myAxios } from "../services/helpler";
// import  "./CustomNavbar.css";

const breakPoints = [
    { width: 1, itemsToShow: 1 },
    { width: 550, itemsToShow: 5 },
    { width: 768, itemsToShow: 5 },
    { width: 1200, itemsToShow: 5},
  ];

const Addedplaces = () => {
  const { id } = useParams();
    const [news, setNews] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    // const [ratingno, setRatingNo] = useState(undefined);
    useEffect(() => {
        axios.get("http://192.168.29.130:5000/api/vehicles/all")
            .then((response) => {
                setNews(response.data);
                console.log(response.data);
                setLoading(true)
            }).catch((error) => {
                setError(error.message);
                setLoading(true);

            })
         
        
        
    }, [])

    if(error){
        return <div>Error:{error.message}</div>;
    }else if(!loading){
        return <div>Loading....</div>;
    }else{

    

    return (
        <>
           

<div className="container-fluid">
          <div className="row">
            <Carousel breakPoints={breakPoints} showArrows={true}>
              {news.map((item, id) => {
                return (
                  <div className="col-12" key={item.id}>
                    <div
                      className="card text-center Hover"
                      style={{ width: "11rem", height: "16rem" }}
                    >
                      <img
                        src={`http://192.168.29.130:5000/api/vehicles/vehicle/image/${item.vehicleImage}`}
                        style={{ width: "164px", height: "100px" }}
                        className="card-img-top mt-1 setsize"
                        alt="..."
                        showArrows={true}
                      />
                      <div className="card-body">
                        <h5 className="card-title h6">
                          {item.model}{" "}
                          <span>
                            <h6 className="text-danger">{item.city}</h6>
                          </span>
                        </h5>
                        <p className="card-text">Mileage:{item.mileage} km.</p>
                        <p className="card-text">{item.fuelType}</p>
                        <p className="card-text text-success ms-5">***** (35)</p>

                        {/* <button
                          onClick={() => {
                            handleGetMore(id);
                          }}
                          className="btn btn-primary mb-2 mar1 "
                        >
                          Get More
                        </button> */}
                      </div>
                    </div>
                  </div>
                );
              })}
            </Carousel>
          </div>
        </div>
        </>
    )
                }
}
export default Addedplaces;