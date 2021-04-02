import React from 'react';
import InputWithLabel from './InputWithLabel';
import FormButton from "./FormButton";


export default class Login extends React.Component {

    componentWillMount() {
        this.setState({
            email: "",
            password: ""
        });
    }

    onEmailChange(data) {
        this.setState({
            email: data
        });
    }

    onPasswordChange(data) {
        this.setState({
            password: data
        });
    }

    onButtonClicked() {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({"email":this.state.email,"password":this.state.password});

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow',
            mode: 'no-cors'
        };

        alert (raw);

        fetch("https://europe-west3-tema3-cloud-309417.cloudfunctions.net/auth", requestOptions)
            .then(response => response.text())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
    }

    render() {
        return (
            <div className="form-login">
                <InputWithLabel label={"email"} type={"email"} onValueChanged={ (data) => {this.onEmailChange(data) }} />
                <InputWithLabel label={"password"} type={"password"} onValueChanged={(data) => {this.onPasswordChange(data)}} />

                <FormButton buttonLabel={"login"} onClick={() => this.onButtonClicked() } />
            </div>
        );
    }
}
