import React from 'react';
import Wrapper from '../hoc/Wrapper';
import { Link } from 'react-router-dom';

 const CreateProjectButton = () =>  {
    return (
        <Wrapper>
            <React.Fragment>
                <Link 
                 to="/addProject"
                 className="btn btn-lg btn-info">
                Create a Project
                </Link>
            </React.Fragment>
        </Wrapper>
    )
}

export default CreateProjectButton;