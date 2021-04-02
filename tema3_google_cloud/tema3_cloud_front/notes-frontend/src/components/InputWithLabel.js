import React from 'react';

export default class InputWithLabel extends React.Component {

    componentWillMount() {
        this.setState({
            value: ""
        });
    }

    onValueChanged(evt) {
        this.setState({
            value: evt.target.value
        });

        this.props.onValueChanged(evt.target.value);
    }

    render() {
        return (
            <div className="input-with-label">

                <h1>{this.props.label}</h1>
                <input value={this.state.value} type={this.props.type} onChange={(evt) => this.onValueChanged(evt)} />

            </div>
        );
    }
}
