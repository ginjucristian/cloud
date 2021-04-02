import React from 'react';

export default class FormButton extends React.Component {



    render() {
        return (
            <div className="form-button">
                <button type="submit" onClick={this.props.onClick} >{this.props.buttonLabel}</button>
            </div>
        );
    }
}
