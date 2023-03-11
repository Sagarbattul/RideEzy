import React from 'react'
import Base from '../components/Base'
import { Row, Col, Container, Card, CardBody, CardHeader, Form, FormGroup, Label, Input, Button, } from "reactstrap"
import god from "../assets/images/god.pdf";
import { useState } from 'react';
import { toast, ToastContainer } from 'react-toastify'
import { loadAllDriverCategories } from '../services/Category-service';
import { getCurrentUserDetail } from '../auth';
import { useEffect } from 'react';
// import { createPost as doCreatePost, uploadPostImage } from '../services/Post-service';
import { uploadDriverImage,
         uploadDriverLicenseImage,
} from '../services/Post-service';
import { createDriver as doCreateDriver } from '../services/Post-service';


const Driver = () => {

    const[user,setUser]=useState(undefined)
    const [categories, setCategories] = useState([]);
    const [post, setPost] = useState({
        d_firstName: '',
        d_lastName: '',
        d_categoryId:'',
        d_altMobNo: '',  
        d_address: '',
        d_city: '',
        d_dob: '',
        d_gender:'',
        d_bloodGroup:'',
        d_ridingExperience:'',
        d_knownLanguages:'',
        d_education:'',
        
      
})

const [image,setImage]=useState(null);
const [licenseImage, setLicenseImage] = useState(null);

useEffect(() => {
    setUser(getCurrentUserDetail()) 
    loadAllDriverCategories().then((data) => {
        console.log(data);
        setCategories(data)
    }).catch(error => {
        console.log(error);

    })
}, [])

const fieldChanged=(event)=>{
    // console.log(event);

   if(event.target.name=="d_altMobNo" && event.target.value.length>=11){
   
     
   }else{


    setPost({...post,[event.target.name]:event.target.value})
    // console.log(event.target.value);
   }

    
}

const createDriver=(event)=>{

    event.preventDefault();
    console.log(post);
    if(post.d_firstName.trim()===''){
      
       toast.error("post firstname is required !!",{
        position:"top-center"});
        
       
    }
    else if(post.d_altMobNo.length!=10){
        toast.error("post phone is required !!",{
            position:"top-center"});
    }
    
    
   
   
    
    else{

    post['userId']=user.id
    doCreateDriver(post).then((data) => {


    uploadDriverImage(image,data.d_id)
    .then((responseForDriverImage)=>{
            toast.success("Image Upload !!",{
                position:"top-center"});
            
            uploadDriverLicenseImage(licenseImage, data.d_id)
            .then((responseForLicenseImage)=>{
                toast.success("Driver License Image Uploaded !",{
                    position:"top-center"
                });


            })

        })
        .catch(error=>{
            toast.error("error in image upload!!",{
                position:"top-center"});
            console.log(error);
            
        })
        toast.success("post create !!",{
            position:"top-center"});
        // console.log(post);
        setPost({
            d_firstName: '',
            d_lastName: '',
            d_categoryId:'',
            d_altMobNo: '',  
            d_address: '',
            d_city: '',
            d_dob: '',
            d_gender:'',
            d_bloodGroup:'',
            d_ridingExperience:'',
            d_knownLanguages:'',
            d_education:'',
           
        })
        
    }).catch((error)=>{
        toast.error("Post not create due to some error",{
            position:"top-center"});
        // console.log(error);
    })   
}
}

const handleFileChange=(event)=>{
    console.log(event.target.files[0]);
    setImage(event.target.files[0])   
}

const handleFileChangeForDriverLicense=(event)=>{
    console.log(event.target.files[0]);
    setLicenseImage(event.target.files[0]);
}



 
  return (
    <div className='bg'>
    <Base>

        <Container >
            <Row className='mt-4'>
                <Col sm={{ size: 12 }}>

                           {/* { JSON.stringify(post)} */}
                    {/* <Card color='dark' inverse> inverse lil tar font color white hoto karan bg dark aahe */}
                    <Card className='changecolor '> {/*inverse lil tar font color white hoto karan bg dark aahe*/}
                        <CardHeader className='bgchange'>
                            <h3 >Fill Information to Driver</h3>
                        </CardHeader>
                        <CardBody>
                            <Form onSubmit={createDriver}>
                                <Row>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="fname">First Name</Label>
                                            <Input type="text" placeholder="Enter Here" id='fname' name="d_firstName" onChange={fieldChanged}/>
                                        </FormGroup>
                                    </Col>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="lname">Last Name</Label>
                                            <Input type="text" placeholder="Enter Here" id='lname' name='d_lastName' onChange={fieldChanged}/>
                                        </FormGroup>
                                    </Col>

                                </Row>

                                <Row>
                                    <Col sm={{size:6}}>
                                    <FormGroup>
                                    <Label for="vmodel">Driver Category: </Label>
                                    <Input type='select' id="vmodel" name='d_categoryId' onChange={fieldChanged} defaultValue={0}>

                                        <option disabled value={0}>--Select Category--</option>
                                        {/* <option value="1">4 wheeler</option> */}
                                        {/* {
                                            categories.map((category)=>(
                                                <option  value={category.categoryId} key={category.categoryId}>
                                                    {category.categoryTitle}
                                                </option>
                                            ))
                                        } */}

                                        {/* <select name="languages" id="vaircon" className='ms-2 p-2 rounded'> */}
                                        {/* <option desabled value={0}>--Select Category--</option> */}

                                        <option value="4">Permanent Driver</option>
                                        <option value="5">Local Trip Driver</option>
                                        <option value="6">OutSide Trip Driver</option>
                                        <option value="7">Transport Driver</option>
                                  

                                    </Input>
                                </FormGroup> 
                                    </Col>
                                    <Col sm={{size:6}}>
                                        {/* <FormGroup>
                                            <Label for="vfule">Vehicle Fule Type :</Label>
                                            <Input type='select' id="vfule" name='fueltype' onChange={fieldChanged} defaultValue={0}>

                                                <select name="languages" id="vaircon" className='ms-2 p-2 rounded'>
                                                <option disabled value={0}>--Select Fule Type--</option>
                                                <option value="petrol">petrol</option>
                                                <option value="desile">desile</option>
                                               
                                            </Input>
                                        </FormGroup> */}
                                         <FormGroup>
                                            <Label for="address">Address</Label>
                                            <Input type="text" placeholder="Enter Here" id='address' name="d_address" onChange={fieldChanged}/>
                                        </FormGroup>
                                    </Col>

                                </Row>

                                <Row>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="city">City</Label>
                                            <Input type="text" placeholder="Enter Here" id='city'name='d_city' onChange={fieldChanged} />
                                        </FormGroup>
                                    </Col>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="dob">DOB</Label>
                                            <Input type="date" placeholder="Enter Here" id='dob' name='d_dob' onChange={fieldChanged}/>
                                        </FormGroup>
                                    </Col>

                                </Row>

                                <Row>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="gender">Gender </Label>
                                            <Input type='select' id="gender" name='d_gender' onChange={fieldChanged} defaultValue={0}>

                                                {/* <select name="languages" id="vaircon" className='ms-2 p-2 rounded'> */}
                                                <option disabled value={0}>--Select Transmission--</option>

                                                <option value="male">Male</option>
                                                <option value="female">Female</option>
                                               
                                            </Input>
                                        </FormGroup>
                                    </Col>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="bg">Blood Group:</Label>
                                            <Input type='text' id="bg" name='d_bloodGroup' onChange={fieldChanged} >

                                                {/* <select name="languages" id="vaircon" className='ms-2 p-2 rounded'> */}
                                             
                                            </Input>
                                        </FormGroup>
                                    </Col>

                                </Row>

                                <Row>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="reading">Riding Experience</Label>
                                            <Input type="text" placeholder="enter riding experience" id='reading' name='d_ridingExperience' onChange={fieldChanged} />
                                        </FormGroup>
                                    </Col>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="language">Language</Label>
                                            <Input type='select' id="language" name='d_knownLanguages' onChange={fieldChanged} defaultValue={0}>

                                                {/* <select name="languages" id="vaircon" className='ms-2 p-2 rounded'> */}
                                                <option disabled value={0}>--Select City--</option>

                                                <option value="javascript">JavaScript</option>
                                                <option value="php">PHP</option>
                                                <option value="java">Java</option>
                                                <option value="golang">Golang</option>
                                                <option value="python">Python</option>
                                                <option value="c#">C#</option>
                                                <option value="C++">C++</option>
                                                <option value="erlang">Erlang</option>
                                            </Input>

                                        </FormGroup>


                                    </Col>

                                </Row>
                                {/* File upload Field */}
                                <Row>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="education">Education</Label>
                                            <Input type="text" placeholder="Enter Education" id='education' name='d_education' onChange={fieldChanged}  />
                                            
                                        </FormGroup>
                                    </Col>
                                    <Col sm={{size:6}}>
                                    <FormGroup>
                                    <Label for="mobile">Mobile </Label>
                                    <Input type='text' placeholder="Enter mobile no" id="mobile"  name='d_altMobNo' onChange={fieldChanged} value={post.d_altMobNo} >

                                        

                                    </Input>
                                </FormGroup>
                                    </Col>
                                </Row>

                                <Row>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="puc">Driver Image</Label>
                                            <Input type="file" id='puc' onChange={handleFileChange}/>
                                        </FormGroup>
                                    </Col>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="insurance">Fitness Certificate</Label>
                                            <Input type="file" id='insurance' />
                                        </FormGroup>
                                    </Col>
                                </Row>

                                <Row>
                                    <Col sm={{size:6}}>
                                        <FormGroup>
                                            <Label for="vmodel"> Driver Agreement</Label>
                                            <Input type="file" placeholder="How much of seats" id='vmodel' />
                                        </FormGroup>
                                    </Col>
                                    <Col sm={{size:6}}>
                                    <FormGroup>
                                    <Label for="dl">Driving Licence</Label>
                                    <Input type="file" id='dl' onChange={handleFileChangeForDriverLicense}/>
                                </FormGroup>
                                    </Col>
                                </Row>
<Row>
                                <Col sm={{size:6}}>
                                              <FormGroup>
                                            <Label for="agreement"> Agreement Image Download</Label><br />
                                            {/* <iframe src={god} width="150px" height="50px">
                                            </iframe> */}

                                            {/* <a href={god} download>
                                            <embed src={god} />
                                            </a> */}
                                            <a href={god}>Download Agreement Page</a>
                                        </FormGroup>
                                            </Col>

                                            <Col sm={{size:6}}>
                                            <FormGroup>
                                            <Input type='checkbox'  id='check'/>{" "}
                                          <Label for="check" >I agree to the terms & condition</Label><br />
                                      </FormGroup>
                                          </Col>
                                            </Row>


                                <Container className='text-center'>
                                    <Button outline color='dark' onClick={createDriver}>Create Post</Button>
                                    <Button color='secondary' type='reset' className='ms-2'>Reset Content</Button>
                                </Container>
                                <ToastContainer/>
                            </Form>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
        </Container>

    </Base>
</div>
  )
}

export default Driver;
