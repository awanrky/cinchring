define(function (require) {
    'use strict';

    // tag::vars[]
    var React = require('react');
    var client = require('./client');
    // end::vars[]

    // tag::app[]
    var App = React.createClass({
        getInitialState: function () {
            return ({readings: []});
        },
        componentDidMount: function () {
            client({method: 'GET', path: '/api/readings'}).done(response => {
                this.setState({readings: response.entity});
            });
        },
        render: function () {
            return (
                <Readings readings={this.state.readings}/>
            )
        }
    })
    // end::app[]

    // tag::employee-list[]
    var Readings = React.createClass({
        render: function () {
            var readings = this.props.readings.map(reading =>
                <Reading reading={reading}/>
            );
            return (
                <table>
                    <tr>
                        <th>Id</th>
                        <th>Value</th>
                        <th>Unit of Measure</th>
                    </tr>
                    {readings}
                </table>
            )
        }
    })
    // end::employee-list[]

    // tag::employee[]
    var Reading = React.createClass({
        render: function () {
            return (
                <tr>
                    <td>{this.props.reading.id}</td>
                    <td>{this.props.reading.value}</td>
                    <td>{this.props.reading.uom.name}</td>
                </tr>
            )
        }
    })
    // end::employee[]

    // tag::render[]
    React.render(
        <App />,
        document.getElementById('react')
    )
    // end::render[]

});


