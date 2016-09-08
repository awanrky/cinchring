
define(function(require) {
    'use strict';

    var rest = require('rest');
    var defaultRequest = require('rest/interceptor/defaultRequest');
    var mime = require('rest/interceptor/mime');
    var errorCode = require('rest/interceptor/errorCode');
    var baseRegistry = require('rest/mime/registry');

    var registry = baseRegistry.child();

    return rest
        .wrap(mime, { registry: registry })
        .wrap(errorCode);

});