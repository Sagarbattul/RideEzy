
import React from "react";
import { useState } from "react";
// import ReactPaginate from "react-paginate";
// import cars from "../assets/images/cars.jpg";
import { myAxios } from "../services/helpler";
import Pagination from "react-bootstrap/Pagination";
import { useNavigate } from "react-router-dom";
const Paginate2wheel = () => {
  const [itemsVeh, setItems] = useState([]);
  const [pageNo, setpageNo] = useState(0);
  const { data } = JSON.parse(localStorage.getItem("data"));
  const numberofpage = (n) => {
    setpageNo(n);
  };

  let items = [];
  for (let number = 1; number < itemsVeh.totalPages; number++) {
    items.push(
      <Pagination.Item
        key={number}
        active={number === pageNo}
        onClick={() => {
          numberofpage(number);
        }}
      >
        {number}
      </Pagination.Item>
    );
  }
  const navigate=useNavigate();
  const handleGetMore=(id)=>{
    navigate({pathname: `/show/${id}`})
  }
  React.useEffect(() => {
    myAxios({
      url: `/api/vehicles/getAllVehiclesByCityAndCategoryWithPagination/city/${data.city}/category/5?pageNumber=${pageNo}&pageSize=3&sortBy=id&sortDir=asc`,
    }).then((res) => {
      console.log(res.data);
      setItems(res.data);
    });
  }, [pageNo]);
  return (
    <>
      <div>
        <div className="container">
          <div className="row m-2">
            {itemsVeh.content &&
              itemsVeh.content.length > 1 &&
              itemsVeh.content.map((item) => {
                return (
                  <div key={item.id} className="col-sm-6 col-md-4  v my-2">
                  <div
                  className="card text-center Hover"
                  style={{ width: "13rem", height: "22rem" }}
                >
                {/* <h5 className="card-title">Id:{item.id}</h5> */}
                <img
                src={`http://192.168.29.130:5000/api/vehicles/vehicle/image/${item.vehicleImage}`}
                style={{ width: "190px", height: "8rem" }}
                className="card-img-top ms-2 mt-2"
                alt="..."
                showArrows={true}
                />
                <div className="card-body">
                        {/* <h6 className='card-subtitle mb-2 text-muted text-center'>{item.}</h6> */}
                        <p className="card-title h6">{item.model}</p>
                        <span>
                        <p className="card-text text-danger">{item.city}</p>
                          </span>
                        <p className="card-text">Mileage:{item.mileage} km</p>
                        <p className="card-text h5">{item.fuelType} </p>
                        <p className="card-text text-success mar">**** (35)</p>
                      <button onClick={()=>{handleGetMore(item.id);}}
                      className="btn btn-primary mb-2 mar1" >
                      Get More
                      </button>
                      </div>
                    </div>
                  </div>
                );
              })
            }
          </div>
        </div>
        <Pagination>{items}</Pagination>
      </div>
    </>
  );
};

export default Paginate2wheel;

