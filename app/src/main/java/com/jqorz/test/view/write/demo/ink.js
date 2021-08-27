var gContext;
var test = {};

//减少context属性设置,请搜索testcode 修改代码
var lastcolor_ = "";
function setPenContext(context, stroke) {
    if (context.lineCap != "round") {
        context.lineCap = "round";
        context.lineJoin = "round";
    }
    if (context.lineWidth != stroke.getWidth())
        context.lineWidth = stroke.getWidth();
    if (lastcolor_ != stroke.getColor()) {
        lastcolor_ = stroke.getColor();
        context.strokeStyle = stroke.getColor();
    }
}
function resetLastInfo() {
    lastcolor_ = "";
}

function round(num) {
    return (0.5 + num) << 0;
}

function testFunc() {
    var context = gContext.context;
    var sx = 100, sy = 100;
    context.fillStyle = "#ff0000";


    for (var i = 0; i < 1000; i++) {
        context.beginPath();
        context.moveTo(sx, sy);
        context.lineTo(sx + i * 2, sy + i);
    }
    context.stroke();
}

function drawPoint(context, point, color) {
    drawOne(context, point.x, point.y, color);
}

function drawOne(context, x, y, color) {

    context.fillStyle = color;
    //context.strokeStyle = "#ff0000";

    context.beginPath();
    context.arc(x, y, 3, 0, 2 * Math.PI, true);
    context.closePath();
    context.fill();
}

MyScript = {
    RecognitionType: {
        TEXT: 'TEXT',
        INK: 'INK',
        MATH: 'MATH',
        SHAPE: 'SHAPE',
        MUSIC: 'MUSIC',
        ANALYZER: 'ANALYZER'
    },
    InputMode: {
        CURSIVE: 'CURSIVE',
        ISOLATED: 'ISOLATED',
        SUPERIMPOSED: 'SUPERIMPOSED',
        VERTICAL: 'VERTICAL'
    },
    InputType: {
        CHAR: 'CHAR',
        WORD: 'WORD',
        SINGLE_LINE_TEXT: 'SINGLE_LINE_TEXT',
        MULTI_LINE_TEXT: 'MULTI_LINE_TEXT'
    },
    ResultDetail: {
        TEXT: 'TEXT',
        WORD: 'WORD',
        CHARACTER: 'CHARACTER'
    },
    ResultType: {
        Math: {
            LATEX: 'LATEX',
            MATHML: 'MATHML',
            SYMBOLTREE: 'SYMBOLTREE',
            OFFICEOPENXMLMATH: 'OFFICEOPENXMLMATH'
        },
        Music: {
            MUSICXML: 'MUSICXML',
            SCORETREE: 'SCORETREE'
        }
    },
    Protocol: {
        WS: 'WebSocket',
        REST: 'REST'
    }
};

// Point
(function (scope) {
    /**
     * Point
     *
     * @class Point
     * @param {Object} [obj]
     * @constructor
     */
    function Point(obj) {
        if (obj) {
            this.x = obj.x;
            this.y = obj.y;
        }
    }

    /**
     * Get x
     *
     * @method getX
     * @returns {Number}
     */
    Point.prototype.getX = function () {
        return this.x;
    }
        ;

    /**
     * Set x
     *
     * @method setX
     * @param {Number} x
     */
    Point.prototype.setX = function (x) {
        this.x = x;
    }
        ;

    /**
     * Get y
     *
     * @method getY
     * @returns {Number}
     */
    Point.prototype.getY = function () {
        return this.y;
    }
        ;

    /**
     * Set y
     *
     * @method setY
     * @param {Number} y
     */
    Point.prototype.setY = function (y) {
        this.y = y;
    }
        ;

    // Export
    scope.Point = Point;
}
)(MyScript);

// Rectangle
(function (scope) {
    /**
     * Rectangle
     *
     * @class Rectangle
     * @param {Object} [obj]
     * @constructor
     */
    function Rectangle(obj) {
        if (obj) {
            this.x = obj.x;
            this.y = obj.y;
            this.width = obj.width;
            this.height = obj.height;
        }
    }

    /**
     * Get top-left x
     *
     * @method getX
     * @returns {Number}
     */
    Rectangle.prototype.getX = function () {
        return this.x;
    }
        ;

    /**
     * Set top-left x
     *
     * @method setX
     * @param {Number} x
     */
    Rectangle.prototype.setX = function (x) {
        this.x = x;
    }
        ;

    /**
     * Get top-left y
     *
     * @method getY
     * @returns {Number}
     */
    Rectangle.prototype.getY = function () {
        return this.y;
    }
        ;

    /**
     * Set top-left y
     *
     * @method setY
     * @param {Number} y
     */
    Rectangle.prototype.setY = function (y) {
        this.y = y;
    }
        ;

    /**
     * Get top-left point
     *
     * @method getTopLeftPoint
     * @returns {Point}
     */
    Rectangle.prototype.getTopLeftPoint = function () {
        var point = new scope.Point();
        point.setX(this.x);
        point.setY(this.y);
        return point;
    }
        ;

    /**
     * Set top-left point
     *
     * @method setTopLeftPoint
     * @param {Point} topLeftPoint
     */
    Rectangle.prototype.setTopLeftPoint = function (topLeftPoint) {
        this.x = topLeftPoint.getX();
        this.y = topLeftPoint.getY();
    }
        ;

    /**
     * Get width
     *
     * @method getWidth
     * @returns {Number}
     */
    Rectangle.prototype.getWidth = function () {
        return this.width;
    }
        ;

    /**
     * Set width
     *
     * @method setWidth
     * @param {Number} width
     */
    Rectangle.prototype.setWidth = function (width) {
        this.width = width;
    }
        ;

    /**
     * Get height
     *
     * @method getHeight
     * @returns {Number}
     */
    Rectangle.prototype.getHeight = function () {
        return this.height;
    }
        ;

    /**
     * Set height
     *
     * @method setHeight
     * @param {Number} height
     */
    Rectangle.prototype.setHeight = function (height) {
        this.height = height;
    }
        ;

    // Export
    scope.Rectangle = Rectangle;
}
)(MyScript);

// PenParameters
(function (scope) {
    /**
     * Parameters used for both input and output canvas draw.
     *
     * @class PenParameters
     * @constructor
     */
    function PenParameters(obj) {
        this.color = 'rgba(255, 0, 0, 255)';//modify by ljm
        this.rectColor = 'rgba(0, 0, 0, 0.2)';
        this.font = 'Times New Roman';
        this.decoration = 'normal';
        this.width = 3;
        this.pressureType = 'SIMULATED';
        this.alpha = '1.0';
        if (obj) {
            this.color = obj.color;
            this.rectColor = obj.rectColor;
            this.font = obj.font;
            this.decoration = obj.decoration;
            this.width = obj.width;
            this.pressureType = obj.pressureType;
            this.alpha = obj.alpha;
        }
    }

    /**
     * Get the color renderer parameter
     *
     * @method getColor
     * @returns {String} The color of the ink
     */
    PenParameters.prototype.getColor = function () {
        return this.color;
    }
        ;

    /**
     * Set the color renderer parameter
     *
     * @method setColor
     * @param {String} color
     */
    PenParameters.prototype.setColor = function (color) {
        this.color = color;
    }
        ;

    /**
     * Get the rect renderer parameter
     *
     * @method getRectColor
     * @returns {String} the rectangle color
     */
    PenParameters.prototype.getRectColor = function () {
        return this.rectColor;
    }
        ;

    /**
     * Set the rect renderer parameter
     *
     * @method setRectColor
     * @param {String} rectColor
     */
    PenParameters.prototype.setRectColor = function (rectColor) {
        this.rectColor = rectColor;
    }
        ;

    /**
     * Get the font renderer parameter
     *
     * @method getFont
     * @returns {String} The font
     */
    PenParameters.prototype.getFont = function () {
        return this.font;
    }
        ;

    /**
     * Set the font renderer parameter
     *
     * @method setFont
     * @param {String} font
     */
    PenParameters.prototype.setFont = function (font) {
        this.font = font;
    }
        ;

    /**
     * Get the decoration renderer parameter
     *
     * @method getDecoration
     * @returns {String} The decoration
     */
    PenParameters.prototype.getDecoration = function () {
        return this.decoration;
    }
        ;

    /**
     * Set the decoration renderer parameter
     *
     * @method setDecoration
     * @param {String} decoration
     */
    PenParameters.prototype.setDecoration = function (decoration) {
        this.decoration = decoration;
    }
        ;

    /**
     * Get the width renderer parameter
     *
     * @method getWidth
     * @returns {Number} The ink width
     */
    PenParameters.prototype.getWidth = function () {
        return this.width;
    }
        ;

    /**
     * Set the width renderer parameter
     *
     * @method setWidth
     * @param {Number} width
     */
    PenParameters.prototype.setWidth = function (width) {
        this.width = width;
    }
        ;

    // Export
    scope.PenParameters = PenParameters;
}
)(MyScript);

// AbstractComponent
(function (scope) {
    /**
     * Represent an abstract input component
     *
     * @class AbstractComponent
     * @constructor
     */
    function AbstractComponent() { }

    /**
     * Get the type of the input component
     *
     * @method getType
     * @returns {String}
     */
    AbstractComponent.prototype.getType = function () {
        return this.type;
    }
        ;

    /**
     * Set the type of the input component
     *
     * @method setType
     * @param {String} type
     */
    AbstractComponent.prototype.setType = function (type) {
        this.type = type;
    }
        ;

    /**
     * Get input component bounding-box
     *
     * @method getBoundingBox
     * @returns {Rectangle}
     */
    AbstractComponent.prototype.getBoundingBox = function () {
        throw new Error('not implemented');
    }
        ;

    /**
     * Set input component bounding-box
     *
     * @method setBoundingBox
     * @param {Rectangle} boundingBox
     */
    AbstractComponent.prototype.setBoundingBox = function (boundingBox) {
        // jshint ignore:line
        throw new Error('not implemented');
    }
        ;

    // Export
    scope.AbstractComponent = AbstractComponent;
}
)(MyScript);

// AbstractTextInputComponent
(function (scope) {
    /**
     * Abstract text input component
     *
     * @class AbstractTextInputComponent
     * @extends AbstractComponent
     * @constructor
     */
    function AbstractTextInputComponent(obj) {
        scope.AbstractComponent.call(this);
        if (obj) {
            if (obj.boundingBox) {
                this.boundingBox = new scope.Rectangle(obj.boundingBox);
            }
        }
    }

    /**
     * Inheritance property
     */
    AbstractTextInputComponent.prototype = new scope.AbstractComponent();

    /**
     * Constructor property
     */
    AbstractTextInputComponent.prototype.constructor = AbstractTextInputComponent;

    /**
     * Get input component bounding-box
     *
     * @method getBoundingBox
     * @returns {Rectangle}
     */
    AbstractTextInputComponent.prototype.getBoundingBox = function () {
        return this.boundingBox;
    }
        ;

    /**
     * Set input component bounding-box
     *
     * @method setBoundingBox
     * @param {Rectangle} boundingBox
     */
    AbstractTextInputComponent.prototype.setBoundingBox = function (boundingBox) {
        this.boundingBox = boundingBox;
    }
        ;

    // Export
    scope.AbstractTextInputComponent = AbstractTextInputComponent;
}
)(MyScript);

// StringInputComponent
(function (scope) {
    /**
     * String input component
     *
     * @class StringInputComponent
     * @extends AbstractTextInputComponent
     * @constructor
     */
    function StringInputComponent(obj) {
        scope.AbstractTextInputComponent.call(this, obj);
        this.type = 'string';
        if (obj) {
            this.options = obj;
        }
    }

    /**
     * Inheritance property
     */
    StringInputComponent.prototype = new scope.AbstractTextInputComponent();

    /**
     * Constructor property
     */
    StringInputComponent.prototype.constructor = StringInputComponent;

    /**
     * Get label
     *
     * @method getLabel
     * @returns {String}
     */
    StringInputComponent.prototype.getOptions = function () {
        return this.options;
    }
        ;

    /**
     * Set label
     *
     * @method setLabel
     * @param {String} label
     */
    StringInputComponent.prototype.setOptions = function (opt) {
        this.options = opt;
    }
        ;

    // Export
    scope.StringInputComponent = StringInputComponent;
}
)(MyScript);

// StrokeComponent
(function (scope) {
    /**
     * Represent a simple StrokeComponent input component
     *
     * @class StrokeComponent
     * @extends AbstractComponent
     * @constructor
     */
    function StrokeComponent(obj) {
        scope.AbstractComponent.call(this);
        this.clear();
        if (obj) {
            if (obj.x) {
                this.x = obj.x;
            }
            if (obj.y) {
                this.y = obj.y;
            }
            if (obj.t) {
                this.t = obj.t;
            }
            if (obj.p) {
                this.p = obj.p;
            }
            if (obj.d) {
                this.d = obj.d;
            }
            if (obj.l) {
                this.l = obj.l;
            }
            if (obj.color) {
                this.color = obj.color;
            }
            if (obj.alpha) {
                this.alpha = obj.alpha;
            }
            if (obj.width) {
                this.width = obj.width;
            }
            if (obj.pentype) {
                this.pentype = obj.pentype;//add by ljm
            }
        }
    }

    /**
     * Inheritance property
     */
    StrokeComponent.prototype = new scope.AbstractComponent();

    /**
     * Constructor property
     */
    StrokeComponent.prototype.constructor = StrokeComponent;

    var TEST_WIDTH = 10;//测试使用，设定固定线宽值 testcode
    StrokeComponent.prototype.clear = function () {
        this.type = 'stroke';
        this.x = [];
        this.y = [];//坐标值
        this.t = [];//时间戳
        this.p = [];//相对压力值，0.1-1.0
        this.d = [];//连续两点之间的距离， 暂时没有使用
        this.l = [];//各个点到起始点的长度, 没有使用
        this.color = undefined;
        this.alpha = undefined;
        this.width = 5;
        this.pentype = "mao";  //testcode 画笔类型= 铅笔pen,毛笔mao. 橡皮擦erase，准备废弃 penorerase
        this.penorerase = 'pen';
        this.pageid = 0;
    }

    /**
     * @method toJSON
     * @returns {Object}
     */
    StrokeComponent.prototype.toJSON = function () {
        return {
            type: this.type,
            x: this.x,
            y: this.y,
            t: this.t
        };
    }
        ;

    /**
     * Get the list of x coordinates
     *
     * @method getX
     * @returns {Number[]}
     */
    StrokeComponent.prototype.getX = function () {
        return this.x;
    }
        ;

    /**
     * Set the list of x coordinates
     *
     * @method setX
     * @param {Number[]} x
     */
    StrokeComponent.prototype.setX = function (x) {
        this.x = x;
    }
        ;

    /**
     * Add a x to the list of x coordinates
     *
     * @method addX
     * @param {Number} x
     */
    StrokeComponent.prototype.addX = function (x) {
        if ((x !== null) && (x !== undefined)) {
            this.x.push(x);
        }
    }
        ;

    /**
     * Get the list of y coordinates
     *
     * @method getY
     * @returns {Number[]}
     */
    StrokeComponent.prototype.getY = function () {
        return this.y;
    }
        ;

    /**
     * Set the list of y coordinates
     *
     * @method setY
     * @param {Number[]} y
     */
    StrokeComponent.prototype.setY = function (y) {
        this.y = y;
    }
        ;

    /**
     * Add a y to the list of y coordinates
     *
     * @method addY
     * @param {Number} y
     */
    StrokeComponent.prototype.addY = function (y) {
        if ((y !== null) && (y !== undefined)) {
            this.y.push(y);
        }
    }
        ;

    /**
     * Get the list of timestamps
     *
     * @method getT
     * @returns {Number[]}
     */
    StrokeComponent.prototype.getT = function () {
        return this.t;
    }
        ;

    /**
     * Set the list of timestamps
     *
     * @method setT
     * @param {Number[]} t
     */
    StrokeComponent.prototype.setT = function (t) {
        this.t = t;
    }
        ;

    /**
     * Add a timestamp to the list
     *
     * @method addT
     * @param {Number} t
     */
    var lasttt = 0;
    StrokeComponent.prototype.addT = function (t) {
        //console.log("T=" + (t - lasttt));//计算时间差，基本都在15.9-16.1毫米之间
        if ((t !== null) && (t !== undefined)) {
            this.t.push(t);
            lasttt = t;
        }
    }
        ;

    StrokeComponent.prototype.getLength = function () {
        return this.x.length;
    }
        ;

    /**
     * Get the boundingBox
     *
     * @method getBoundingBox
     * @returns {Rectangle}
     */
    StrokeComponent.prototype.getBoundingBox = function () {
        var boundingBox = new scope.Rectangle();
        boundingBox.setX(Math.min.apply(Math, this.getX()));
        boundingBox.setY(Math.min.apply(Math, this.getY()));
        boundingBox.setWidth(Math.max.apply(Math, this.getX()) - boundingBox.getX());
        boundingBox.setHeight(Math.max.apply(Math, this.getY()) - boundingBox.getY());
        return boundingBox;
    }
        ;

    StrokeComponent.prototype.toFixed = function (precision) {
        if (precision !== undefined) {
            for (var i in this.x) {
                this.x[i] = this.x[i].toFixed(precision);
                this.y[i] = this.y[i].toFixed(precision);
            }
        }
    }
        ;

    StrokeComponent.prototype.getP = function () {
        return this.p;
    }
        ;

    StrokeComponent.prototype.setP = function (p) {
        this.p = p;
    }
        ;

    StrokeComponent.prototype.addP = function (p) {
        if ((p !== null) && (p !== undefined)) {
            this.p.push(p);
        }
    }
        ;

    StrokeComponent.prototype.getD = function () {
        return this.d;
    }
        ;

    StrokeComponent.prototype.setD = function (d) {
        this.d = d;
    }
        ;

    StrokeComponent.prototype.addD = function (d) {
        if ((d !== null) && (d !== undefined)) {
            this.d.push(d);
        }
    }
        ;

    StrokeComponent.prototype.getL = function () {
        return this.l;
    }
        ;

    StrokeComponent.prototype.setL = function (l) {
        this.l = l;
    }
        ;

    StrokeComponent.prototype.addL = function (l) {
        if ((l !== null) && (l !== undefined)) {
            this.l.push(l);
        }
    }
        ;

    StrokeComponent.prototype.getColor = function () {
        return this.color;
    }
        ;

    StrokeComponent.prototype.setColor = function (color) {
        this.color = color;
    }
        ;

    StrokeComponent.prototype.getWidth = function () {
        // return this.width;
        return TEST_WIDTH; //add by ljm
    }
        ;

    StrokeComponent.prototype.setWidth = function (width) {
        this.width = width;
    }
        ;

    StrokeComponent.prototype.setPenOrErase = function (value) {
        this.penorerase = value;
    }
        ;

    StrokeComponent.prototype.getPenOrErase = function () {
        return this.penorerase;
    }
        ;

    StrokeComponent.prototype.setPageid = function (value) {
        this.pageid = value;
    }
        ;

    StrokeComponent.prototype.getPageid = function (value) {
        return this.pageid;
    }
        ;

    StrokeComponent.prototype.isMaoPen = function (value) {
        return this.pentype == "mao";
    }

    StrokeComponent.prototype.addPoint = function (x, y, t) {
        console.log("addpoint:x=" + x + ",y=" + y + ",t=" + t + ",getlength=" + this.getLength());
        if (_filterPointByAcquisitionDelta(x, y, this.getX(), this.getY(), this.getLastIndexPoint(), this.getWidth(), this.getLength())) {
            //drawOne(gContext.context, x, y, "#ff00ff");
            //移动的点超过阈值才计算，
            this.addX(x);
            this.addY(y);
            this.addT(t);
            if (this.isMaoPen()) {//add by ljm 只有毛笔才计算，节省cpu
                var distance = _computeDistance(x, y, this.getX(), this.getY(), this.getLastIndexPoint());
                var length = _computeLength(x, y, this.getX(), this.getY(), this.getL(), this.getLastIndexPoint());
                //this.addD(distance);
                if (this.penorerase == 'pen') {
                    var lastp = this.p[this.getLastIndexPoint() - 1];
                    var currentp = _computePressureLjm(distance, length);
                    console.log("lastp=" + lastp + ",currentp=" + currentp + ",xi=" + lastp / currentp);
                    var maxp = 0.1;
                    var di = currentp - lastp;
                    if (lastp != 0 && Math.abs(di) > lastp * maxp) {//强制增减幅度不超过10%
                        currentp = (di > 0 ? (1 + maxp) : (1 - maxp)) * lastp;
                        currentp = currentp > 1 ? 1.0 : currentp;
                        console.log("覆盖值,cp=" + currentp);
                    }

                    this.addP(currentp);
                }
                else {
                    this.addP(0.5);
                }

                this.addL(length);
            }
            return true;
        }
        return false;//add by ljm 防止过度绘制
    }
        ;

    StrokeComponent.prototype.getLastIndexPoint = function () {
        return this.x.length - 1;
    }
        ;

    StrokeComponent.prototype.getPointByIndex = function (index) {
        var point;
        if (index !== undefined && index >= 0 && index < this.getLength()) {
            point = {
                x: this.getX()[index],
                y: this.getY()[index],
                t: this.getT()[index],
                p: this.getP()[index],
                d: this.getD()[index],
                l: this.getL()[index]
            };
        }
        return point;
    }
        ;

    function _computeDistance(x, y, xArray, yArray, lastIndexPoint) {
        var distance = Math.sqrt(Math.pow((y - yArray[lastIndexPoint - 1]), 2) + Math.pow((x - xArray[lastIndexPoint - 1]), 2));

        if (isNaN(distance)) {
            distance = 0;
        }

        return distance;
    }

    function _computeLength(x, y, xArray, yArray, lArray, lastIndexPoint) {
        var length = lArray[lastIndexPoint - 1] + _computeDistance(x, y, xArray, yArray, lastIndexPoint);

        if (isNaN(length)) {
            length = 0;
        }

        return length;
    }


    //节省算法 add by ljm
    function _computePressureLjm(distance, length) {
        var ratio = 1.0;
        if (length === 0) {
            ratio = 0.5;
        } else if (distance == length) {
            ratio = 1.0;
        } else if (distance < 10) {
            ratio = 0.2 + Math.pow(0.1 * distance, 0.4);
        } else if (distance > length - 10) {
            ratio = 0.2 + Math.pow(0.1 * (length - distance), 0.4);
        }
        var pressure = ratio * Math.max(0.1, 1.0 - 0.1 * Math.sqrt(distance));
        if (isNaN(parseFloat(pressure))) {
            pressure = 0.5;
        }
        return pressure;
    }


    //原始压力算法
    function _computePressure(x, y, xArray, yArray, lArray, lastIndexPoint) {
        var ratio = 1.0;
        var distance = _computeDistance(x, y, xArray, yArray, lastIndexPoint);
        var length = _computeLength(x, y, xArray, yArray, lArray, lastIndexPoint);

        if (length === 0) {
            ratio = 0.5;
        } else if (distance == length) {
            ratio = 1.0;
        } else if (distance < 10) {
            ratio = 0.2 + Math.pow(0.1 * distance, 0.4);
        } else if (distance > length - 10) {
            ratio = 0.2 + Math.pow(0.1 * (length - distance), 0.4);
        }
        var pressure = ratio * Math.max(0.1, 1.0 - 0.1 * Math.sqrt(distance));
        if (isNaN(parseFloat(pressure))) {
            pressure = 0.5;
        }
        return pressure;
    }

    function _filterPointByAcquisitionDelta(x, y, xArray, yArray, lastIndexPoint, width, length) {
        var delta = (2 + (width / 4));
        var ret = false;
        if (length === 0 || Math.abs(xArray[lastIndexPoint] - x) >= delta || Math.abs(yArray[lastIndexPoint] - y) >= delta) {
            ret = true;
        }
        return ret;
    }

    // Export
    scope.StrokeComponent = StrokeComponent;
}
)(MyScript);

(function (scope) {

    function ShapeComponent(obj) {
        scope.AbstractComponent.call(this);
        this.clear();
        if (obj) {
            this.sPoint.x = obj.sPoint.x;
            this.sPoint.y = obj.sPoint.y;
            this.ePoint.x = obj.ePoint.x;
            this.ePoint.y = obj.ePoint.y;
        }
    }

    ShapeComponent.prototype = new scope.AbstractComponent();

    /**
     * Constructor property
     */
    ShapeComponent.prototype.constructor = ShapeComponent;

    ShapeComponent.prototype.clear = function () {
        this.shape = 0;
        this.empty = true;
        this.sPoint = new scope.Point();
        this.ePoint = new scope.Point();
    }

    ShapeComponent.prototype.startPoint = function (x, y) {
        this.shape = 1;
        this.empty = false;
        this.sPoint.x = x;
        this.sPoint.y = y;
    }

    ShapeComponent.prototype.endPoint = function (x, y) {
        this.ePoint.x = x;
        this.ePoint.y = y;
    }

    ShapeComponent.prototype.isEmpty = function () {
        return this.empty;
    }

    ShapeComponent.prototype.getColor = function () {
        return this.color;
    }
        ;

    ShapeComponent.prototype.setColor = function (color) {
        this.color = color;
    }
        ;

    ShapeComponent.prototype.getWidth = function () {
        return this.width;
    }
        ;

    ShapeComponent.prototype.setWidth = function (width) {
        this.width = width;
    }
        ;

    scope.ShapeComponent = ShapeComponent;
}
)(MyScript);

// PageComponent
(function (scope) {
    /**
     * Represent a simple PageComponent input component
     *
     * @class PageComponent
     * @extends AbstractComponent
     * @constructor
     */
    function PageComponent(pageid) {
        scope.AbstractComponent.call(this);
        this.type = 'pagecom';
        this.clear();
        this.pageid = pageid;
    }

    /**
     * Inheritance property
     */
    PageComponent.prototype = new scope.AbstractComponent();

    /**
     * Constructor property
     */
    PageComponent.prototype.constructor = PageComponent;

    PageComponent.prototype.clear = function () {
        this.strokecomponent = [];
        this.redocomponent = [];
    }

    PageComponent.prototype.setPageid = function (value) {
        this.pageid = value;
    }
        ;

    PageComponent.prototype.getPageid = function (value) {
        return this.pageid;
    }
        ;

    PageComponent.prototype.push = function (stroke) {
        this.strokecomponent.push(stroke);
    }
        ;

    PageComponent.prototype.redo = function (stroke) {
        this.redocomponent.push(stroke);
    }
        ;

    PageComponent.prototype.popStroke = function () {
        return this.strokecomponent.pop();
    }
        ;

    PageComponent.prototype.popredoStroke = function () {
        return this.redocomponent.pop();
    }
        ;

    PageComponent.prototype.getStroke = function () {
        return this.strokecomponent;
    }
        ;

    PageComponent.prototype.getredoStroke = function () {
        return this.redocomponent;
    }
        ;
    // Export
    scope.PageComponent = PageComponent;
}
)(MyScript);

// AbstractRenderer
(function (scope) {
    /**
     * Represent the Abstract Renderer. It's used to calculate the ink rendering in HTML5 canvas
     *
     * @class AbstractRenderer
     * @param {Object} context
     * @constructor
     */
    function AbstractRenderer(context) {
        this.penParameters = new scope.PenParameters();
        this.showBoundingBoxes = false;
        this.typeset = true;
        this.context = context;
    }

    /**
     * Get the context
     *
     * @returns {Object}
     */
    AbstractRenderer.prototype.getContext = function () {
        return this.context;
    }
        ;

    /**
     * This property is use to show or not show the bounding box
     *
     * @method getShowBoundingBoxes
     * @returns {Boolean}
     */
    AbstractRenderer.prototype.getShowBoundingBoxes = function () {
        return this.showBoundingBoxes;
    }
        ;

    /**
     * Set the show state of bounding box
     *
     * @method setShowBoundingBoxes
     * @param {Boolean} showBoundingBoxes
     */
    AbstractRenderer.prototype.setShowBoundingBoxes = function (showBoundingBoxes) {
        this.showBoundingBoxes = showBoundingBoxes;
    }
        ;

    /**
     * Get the default pen parameters
     *
     * @returns {PenParameters}
     */
    AbstractRenderer.prototype.getParameters = function () {
        return this.penParameters;
    }
        ;

    /**
     * Set the default pen parameters
     *
     * @param {PenParameters} penParameters
     */
    AbstractRenderer.prototype.setParameters = function (penParameters) {
        this.penParameters = penParameters;
    }
        ;

    /**
     * Is typesetting
     *
     * @returns {Boolean}
     */
    AbstractRenderer.prototype.isTypesetting = function () {
        return this.typeset;
    }
        ;

    /**
     * Enable / disable typesetting
     *
     * @param {Boolean} typeset
     */
    AbstractRenderer.prototype.setTypeset = function (typeset) {
        this.typeset = typeset;
    }
        ;

    /**
     * Clear the recognition context
     *
     * @method clear
     */
    AbstractRenderer.prototype.clear = function () {
        this.getContext().clearRect(0, 0, this.getContext().canvas.width, this.getContext().canvas.height);

    }
        ;

    /**
     * Draw recognition result on HTML5 canvas.
     *
     * @method drawRecognitionResult
     * @param {AbstractComponent[]} components
     * @param {Object} recognitionResult
     */
    AbstractRenderer.prototype.drawRecognitionResult = function (components, recognitionResult) {
        // jshint ignore:line
        throw new Error('not implemented');
    }
        ;

    /**
     * Draw input components
     *
     * @method drawComponents
     * @param {AbstractComponent[]} components
     */
    AbstractRenderer.prototype.drawComponents = function (components) {
        // jshint ignore:line
        throw new Error('not implemented');
    }
        ;

    /**
     * Draw component
     *
     * @method drawComponent
     * @param {AbstractComponent} component
     */
    AbstractRenderer.prototype.drawComponent = function (component, isDrawAll) {
        var isdrawall = (arguments.length == 2) ? arguments[1] : true;//add by ljm 
        if (component instanceof scope.StrokeComponent) {
            _drawStroke(component, this.getContext(), this.getParameters(), isdrawall, this);
        } else if (component instanceof scope.ShapeComponent) {
            _drawShape(component, this.getContext(), this.getParameters());
        } else if (component instanceof scope.CharacterInputComponent) {
            _drawCharacter(component, this.getContext(), this.getParameters());
        } else {
            throw new Error('Component not implemented: ' + component.getType());
        }
    }
        ;

    /**
     * Draw stroke component
     *
     * @private
     * @method _drawStroke
     * @param {StrokeComponent} stroke
     * @param {Object} context
     * @param {PenParameters} parameters
     */
    var _drawStroke = function (stroke, context, parameters, isDrawAll, InkGrabber) {
        // jshint ignore:line
        if (stroke && stroke.getLength() > 0) {
            _renderStroke(stroke, context, isDrawAll, InkGrabber);
        }
    };

    var _drawShape = function (stroke, context, parameters) {
        // jshint ignore:line
        if (stroke && stroke.isEmpty() == false) {
            _renderShape(stroke, context);
        }
    };

    /**
     * Draw character component
     *
     * @private
     * @method _drawCharacter
     * @param {CharacterInputComponent} character
     * @param {Object} context
     * @param {PenParameters} parameters
     */
    var _drawCharacter = function (character, context, parameters) {
        // jshint ignore:line
        throw new Error('not implemented');
    };

    /**
     * Draw a rectangle on context
     *
     * @private
     * @method _drawRectangle
     * @param {Rectangle} rectangle
     * @param {Object} context
     * @param {PenParameters} parameters
     */
    var _drawRectangle = function (rectangle, context, parameters) {
        context.save();
        try {
            context.fillStyle = parameters.getRectColor();
            context.strokeStyle = parameters.getColor();
            context.lineWidth = 0.5 * parameters.getWidth();
            context.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        } finally {
            context.restore();
        }
    };

    /*******************************************************************************************************************
     * Algorithm methods to compute rendering
     ******************************************************************************************************************/

    //计算出平行点
    function computeLinksPoints(point, angle, width) {
        var radius = point.p * width;
        //console.log("radius=" + radius);//提笔/划线，时候angle是负值，所以第一个点x增加，y增加，第二个点，x减小，y减小
        //可以思考极端的情况，水平对齐，垂直运动
        return [{
            x: (point.x - Math.sin(angle) * radius),
            y: (point.y + Math.cos(angle) * radius)
        }, {
            x: (point.x + Math.sin(angle) * radius),
            y: (point.y - Math.cos(angle) * radius)
        }];
    }

    function computeMiddlePoint(point1, point2) {
        return {
            x: ((point2.x + point1.x) / 2),
            y: ((point2.y + point1.y) / 2),
            p: ((point2.p + point1.p) / 2)
        };
    }

    //计算两个移动点的方向角（bengin (1,7) end (7,1) Math.atan2（-6,6）= -45度，）
    function _computeAxeAngle(begin, end) {
        return Math.atan2(end.y - begin.y, end.x - begin.x);
    }

    function _fill(context, color) {
        if (color !== undefined) {
            context.fillStyle = color;
            context.fill();
        }
    }

    function _renderMaoStroke(stroke, context, isDrawAll, inkgrabber) {
        console.log("_renderMaoStroke");
        context.beginPath();
        var length = stroke.getLength();
        var width = stroke.getWidth();

        //console.log("_renderStroke,stroke.width=" + width);
        if (stroke.getPenOrErase() == 'pen')
            context.globalCompositeOperation = "source-over";
        else
            context.globalCompositeOperation = "destination-out";
        var firstPoint = stroke.getPointByIndex(0);
        if (length < 3) {
            console.log("绘制小3");
            context.arc(firstPoint.x, firstPoint.y, width * 0.5, 0, Math.PI * 2, true);
        } else {
            if (!inkgrabber.hasDrawHead || isDrawAll) {
                inkgrabber.hasDrawHead = true;
                console.log("绘制头部,radius=" + width * firstPoint.p);
                context.arc(firstPoint.x, firstPoint.y, width * firstPoint.p, 0, Math.PI * 2, true);
                _renderLine(context, firstPoint, computeMiddlePoint(firstPoint, stroke.getPointByIndex(1)), width);
            }
            //Possibility to try this (the start looks better when the ink is large)
            //var first = computeMiddlePoint(stroke.getPointByIndex(0), stroke.getPointByIndex(1));
            //context.arc(firstPoint.x, firstPoint.y, width * firstPoint.p, 0, Math.PI * 2, true);

            var nbquadratics = length - 2;
            var i = nbquadratics - 1;//绘制最后一个点
            if (isDrawAll || !inkgrabber.hasArealyDrawBody) {
                i = 0;//从头开始, 第一次绘制如果length！=3 则强制从0开始绘制
            }
            inkgrabber.hasArealyDrawBody = true;
            for (; i < nbquadratics; i++) {
                //console.log("绘制中部+"+i);
                //连续两个中点 作为起止点， 控制点为下一个点，
                renderQuadratic(context, computeMiddlePoint(stroke.getPointByIndex(i), stroke.getPointByIndex(i + 1)), computeMiddlePoint(stroke.getPointByIndex(i + 1), stroke.getPointByIndex(i + 2)), stroke.getPointByIndex(i + 1), width);
            }

            //绘制尾部
            if (isDrawAll) {
                console.log("绘制尾部");
                _renderLine(context, computeMiddlePoint(stroke.getPointByIndex(length - 2), stroke.getPointByIndex(length - 1)), stroke.getPointByIndex(length - 1), width);
                renderFinal(context, stroke.getPointByIndex(length - 2), stroke.getPointByIndex(length - 1), width);
            }
        }
        context.closePath();
        //_fileStroke(context, stroke);//测试代码,可以绘制轮廓，和下行代码互斥,testcode
        _fill(context, stroke.getColor());
    }



    var iii = 0;
    //绘制铅笔固定线宽 add byljm
    function _renderPenStroke(stroke, context, isDrawAll, inkgrabber) {
        console.log("_renderPenStroke");
        context.beginPath();
        setPenContext(context, stroke);
        //iii++;
        //if (iii % 2 != 0)
        //    context.strokeStyle = 'rgb(255, 0, 0 )';
        //else if (iii % 2 == 0) {
        //    iii = 0;
        //    context.strokeStyle = 'rgb(0, 255, 0)';
        //}

        var length = stroke.getLength();
        var firstPoint = stroke.getPointByIndex(0);
        if (length < 3) {
            //context.arc(firstPoint.x, firstPoint.y, context.lineWidth * 0.6, 0, Math.PI * 2, true);
        } else {
            if (isDrawAll || !inkgrabber.hasArealyDrawBody) {
                inkgrabber.hasArealyDrawBody = true;
                //全量绘制
                context.moveTo(firstPoint.x, firstPoint.y);
                for (var i = 1; i < length; i++) {
                    var lastPoint = stroke.getPointByIndex(i - 1);//上一个点
                    var p = stroke.getPointByIndex(i);//当前点
                    var cpx1 = (p.x + lastPoint.x) / 2;
                    var cpy1 = (p.y + lastPoint.y) / 2;
                    //使用非浮点数据，提升性能
                    context.quadraticCurveTo(lastPoint.x, lastPoint.y, round(cpx1), round(cpy1));
                }
            } else {//增量绘制
                var lastlast = stroke.getPointByIndex(stroke.getLastIndexPoint() - 2);
                var lastPoint = stroke.getPointByIndex(stroke.getLastIndexPoint() - 1);//上一个点
                var currentP = stroke.getPointByIndex(stroke.getLastIndexPoint());
                context.moveTo(round((lastlast.x + lastPoint.x) / 2), round((lastlast.y + lastPoint.y) / 2));
                context.quadraticCurveTo(lastPoint.x, lastPoint.y, round((currentP.x + lastPoint.x) / 2), round((currentP.y + lastPoint.y) / 2));
            }
        }
        context.stroke();
    }


    /**
     *渲染
     策略是每添加一个点都要先擦除之前的笔迹然后重绘制，，因为尾部是绘制出来的，新增一个点，上一个点的尾部失效了。
     优化策略：up事件的时候才绘制尾部，其他时候只绘制连续点。
     先实现现有的策略，然后再优化。add by ljm
     * @param stroke
     * @param context
     * @param parameters
     * @private
     */
    var countljm = 0;
    function _renderStroke(stroke, context, isDrawAll, inkgrabber) {
        //showtime("rs");
        if (stroke.isMaoPen()) {
            _renderMaoStroke(stroke, context, isDrawAll, inkgrabber);
        }
        else {
            _renderPenStroke(stroke, context, isDrawAll, inkgrabber);
        }
        //showtime("re");
        //console.log(" ");
    }

    // add by ljm，填充轮廓
    function _fileStroke(context, stroke) {
        context.lineWidth = 1;
        context.strokeStyle = stroke.getColor();
        context.stroke();

    }


    function _renderShape(shape, context) {
        var s = shape.sPoint;
        var e = shape.ePoint;
        var width = shape.getWidth();

        context.lineWidth = width;
        context.strokeStyle = shape.getColor();
        context.beginPath();
        switch (shape.shape) {
            case 1:
                context.moveTo(s.x, s.y);
                context.lineTo(s.x, s.y);
                context.lineTo(e.x, e.y);
                break;
        }

        context.closePath();
        context.stroke();
    }

    //绘制尾部近似圆弧，TODO 如果考虑绘制效果可以和头部一样用圆弧代替。请保留此方法，很有参考性质，
    function renderFinal(context, begin, end, width) {
        var ARCSPLIT = 6;
        var angle = _computeAxeAngle(begin, end);
        var linkPoints = computeLinksPoints(end, angle, width);
        context.moveTo(linkPoints[0].x, linkPoints[0].y);
        for (var i = 1; i <= ARCSPLIT; i++) {
            var newAngle = angle - i * Math.PI / ARCSPLIT;
            var p = {};
            p.x = end.x - end.p * width * Math.sin(newAngle);
            p.y = end.y + end.p * width * Math.cos(newAngle);
            context.lineTo(p.x, p.y);
        }
    }

    //计算梯形对称点 add by ljm
    function computeArcPoints(point, angle, width) {
        var radius = point.p * width;
        var newAngle = angle + Math.PI * 0.166;
        var newAngle2 = angle + Math.PI * 0.833;
        return [{
            x: (point.x - Math.sin(newAngle) * radius),
            y: (point.y + Math.cos(newAngle) * radius)
        }, {
            x: (point.x - Math.sin(newAngle2) * radius),
            y: (point.y + Math.cos(newAngle2) * radius)
        }];
    }

    function _renderLine(context, begin, end, width) {
        var linkPoints1 = computeLinksPoints(begin, _computeAxeAngle(begin, end), width);//第一排下方两个点，
        var linkPoints2 = computeLinksPoints(end, _computeAxeAngle(begin, end), width);//第二排上方两个点

        //drawOne(context, begin.x, begin.y, "#f0f0f0");
        //drawOne(context, end.x, end.y, "#0f0f0f");
        //drawOne(context,linkPoints1[0].x, linkPoints1[0].y, "#ff0000");
        //drawOne(context, linkPoints2[0].x, linkPoints2[0].y, "#00ff00");
        //drawOne(context, linkPoints2[1].x, linkPoints2[1].y, "#0000ff");
        //drawOne(context, linkPoints1[1].x, linkPoints1[1].y, "#000000");

        context.moveTo(linkPoints1[0].x, linkPoints1[0].y);
        context.lineTo(linkPoints2[0].x, linkPoints2[0].y);
        context.lineTo(linkPoints2[1].x, linkPoints2[1].y);
        context.lineTo(linkPoints1[1].x, linkPoints1[1].y);
    }


    //绘制中段 回路,采用优化算法时候因为canvas自动抗锯齿且不可取消，导致了中段的竖线是有抗锯齿的，绘制完成之后会出现淡淡的细线，所以需要
    //在isDrawAll=false 的时候绘制一个连接梯形，保证能够抵消抗锯齿带来的淡边。
    function renderQuadratic(context, begin, end, ctrl, width, isDrawAll) {
        //console.log("quad:bengin");
        var linkPoints1 = computeLinksPoints(begin, _computeAxeAngle(begin, ctrl), width);
        var linkPoints2 = computeLinksPoints(end, _computeAxeAngle(ctrl, end), width);
        var linkPoints3 = computeLinksPoints(ctrl, _computeAxeAngle(begin, end), width);//存储控制点

        if (isDrawAll) {
            //console.log("r1,linkPoints1[0].x=" + linkPoints1[0].x + ",linkPoints1[0].y=" + linkPoints1[0].y + ", linkPoints3[0].x=" + linkPoints3[0].x + ", linkPoints3[0].y=" + linkPoints3[0].y + ", linkPoints2[0].x=" + linkPoints2[0].x + ", linkPoints2[0].y=" +linkPoints2[0].y);
            context.moveTo(linkPoints1[0].x, linkPoints1[0].y);//逆时针，回路，
            context.quadraticCurveTo(linkPoints3[0].x, linkPoints3[0].y, linkPoints2[0].x, linkPoints2[0].y);
            //console.log("r2,linkPoints2[1].x=" + linkPoints2[1].x + ",linkPoints2[1].y=" + linkPoints2[1].y + ", linkPoints3[1].x=" + linkPoints3[1].x + ", linkPoints3[1].y=" + linkPoints3[1].y + ", linkPoints1[1].x=" + linkPoints1[1].x + ", linkPoints1[1].y=" +linkPoints1[1].y);
            context.lineTo(linkPoints2[1].x, linkPoints2[1].y);
            context.quadraticCurveTo(linkPoints3[1].x, linkPoints3[1].y, linkPoints1[1].x, linkPoints1[1].y);
            // context.lineTo(linkPoints1[0].x + 5, linkPoints1[0].y);
        }
        else {//增加一个梯形绘制解决垂直线段遗留抗锯齿问题
            var linkPointsArc = computeArcPoints(begin, _computeAxeAngle(begin, ctrl), width);
            //drawPoint(context, linkPointsArc[0], "#ff0000");
            context.moveTo(linkPointsArc[0].x, linkPointsArc[0].y);

            context.lineTo(linkPoints1[0].x, linkPoints1[0].y);//逆时针，回路，
            context.quadraticCurveTo(linkPoints3[0].x, linkPoints3[0].y, linkPoints2[0].x, linkPoints2[0].y);
            context.lineTo(linkPoints2[1].x, linkPoints2[1].y);
            context.quadraticCurveTo(linkPoints3[1].x, linkPoints3[1].y, linkPoints1[1].x, linkPoints1[1].y);

            context.lineTo(linkPointsArc[1].x, linkPointsArc[1].y);
        }
    }

    // Export
    scope.AbstractRenderer = AbstractRenderer;
}
)(MyScript);

// InkGrabber
(function (scope) {
    /**
     * The InkGrabber class that render, capture and build strokes
     *
     * @class InkGrabber
     * @extends AbstractRenderer
     * @param {Object} context
     * @constructor
     */
    function InkGrabber(context) {
        scope.AbstractRenderer.call(this, context);
        this.stroke = undefined;
        this.writing = false;
        this.hasDrawHead = false;
        this.hasArealyDrawBody = false;
        this.penorerase = 'pen';
        this.currentPageNo = 0;
    }

    /**
     * Inheritance property
     */
    InkGrabber.prototype = new scope.AbstractRenderer();

    /**
     * Constructor property
     */
    InkGrabber.prototype.constructor = InkGrabber;

    /**
     * Is Writing a stroke
     *
     * @method isWriting
     * @returns {Boolean}
     */
    InkGrabber.prototype.isWriting = function () {
        return this.writing;
    }
        ;

    InkGrabber.prototype.setPenOrErase = function (value) {
        this.penorerase = value;
        if (this.penorerase == 'pen') {
            this.penParameters.setColor('rgba(255, 0, 0, 1)');
            this.penParameters.setWidth(2);// add by ljm
        } else {
            this.penParameters.setColor('rgba(255, 255, 255, 1)');
            this.penParameters.setWidth(30);
        }
        // this.stroke.penorerase
    }
        ;

    InkGrabber.prototype.setPenColor = function (value) {
        this.penParameters.setColor(value);
    }
        ;

    InkGrabber.prototype.setPenWidth = function (value) {
        this.penParameters.setWidth(value);
    }
        ;

    InkGrabber.prototype.getPenOrErase = function () {
        return this.penorerase;
    }
        ;

    InkGrabber.prototype.setcurrPageNo = function (value) {
        this.currentPageNo = value;
    }
        ;

    InkGrabber.prototype.getcurrPageNo = function () {
        return this.currentPageNo;
    }
        ;

    /**
     * Get the last wrote stroke
     *
     * @method getStroke
     * @returns {StrokeComponent}
     */
    InkGrabber.prototype.getStroke = function () {
        return this.stroke;
    }
        ;

    InkGrabber.prototype.getShape = function () {
        return this.shape;
    }

    InkGrabber.prototype.startCapture = function (x, y, t) {
        if (!this.writing) {
            // this.writing = true;
            this.shape = null;
            this.stroke = new scope.StrokeComponent();
            this.stroke.setColor(this.penParameters.getColor());
            this.stroke.setWidth(this.penParameters.getWidth());
            this.stroke.setPenOrErase(this.penorerase);
            this.stroke.setPageid(this.currentPageNo);
            this.stroke.addPoint(x, y, t);
            this.clear();
            // this.drawComponent(this.stroke);
        } else {// throw new Error('StrokeComponent capture already running');
        }
    }
        ;

    InkGrabber.prototype.drawInk = function (x, y, t) {
        if (this.writing) {
            if (this.stroke.addPoint(x, y, t)) {//add by ljm
                //this.clear();//清空画布，remove by ljm 优化算法无需清空画布
                this.drawComponent(this.stroke, false);
            }
        }
    }
        ,

        InkGrabber.prototype.drawLine = function (sx, sy, ex, ey) {

            this.shape = new scope.ShapeComponent();
            this.shape.startPoint(sx, sy);
            this.shape.endPoint(ex, ey);
            this.shape.setColor(this.penParameters.getColor());
            this.shape.setWidth(this.penParameters.getWidth());

            this.clear();
            this.drawComponent(this.shape);
        }
        ;

    InkGrabber.prototype.continueCapture = function (x, y, t) {

        if (!this.writing) {
            if (this.stroke.getLength() > 0) {
                var xx = this.stroke.getX()[0] - x;
                var yy = this.stroke.getY()[0] - y;
                var size = Math.sqrt(xx * xx + yy * yy);
                if (size >= 2) {
                    this.writing = true;
                }
            }

            this.stroke.addPoint(x, y, t);
        }

        return this.writing;
    }
        ;

    InkGrabber.prototype.endCapture = function (x, y, t) {
        console.log("endCapture");
        if (this.writing && this.stroke.getLength() > 0) {
            this.stroke.addPoint(x, y, t);
            this.clear();
            //this.drawComponent(this.stroke,true); //remove by ljm，最后一个无需绘制，立马就重新绘制了
            this.resetData();
        } else {
            this.resetData();
            this.stroke.clear();
            // throw new Error('Missing startInkCapture');
        }
    }
        ;

    InkGrabber.prototype.clearCurrent = function () {
        if (this.writing) {
            this.stroke.clear();
            this.clear();
            this.drawComponent(this.stroke);
        }
    }

    InkGrabber.prototype.resetData = function () {
        this.writing = false;
        this.hasDrawHead = false;
        this.hasArealyDrawBody = false;
    }

    InkGrabber.prototype.isWriting = function () {
        return this.writing;
    }

    // Export
    scope.InkGrabber = InkGrabber;
}
)(MyScript);

(function (scope) {
    /**
     * Represent the Text Renderer. It's used to calculate the text ink rendering in HTML5 canvas
     *
     * @class TextRenderer
     * @extends AbstractRenderer
     * @param {Object} context
     * @constructor
     */
    function InkRenderer(context) {
        scope.AbstractRenderer.call(this, context);
    }

    /**
     * Inheritance property
     */
    InkRenderer.prototype = new scope.AbstractRenderer();

    /**
     * Constructor property
     */
    InkRenderer.prototype.constructor = InkRenderer;

    /**
     * Draw text recognition result on HTML5 canvas. Scratch out results are use to redraw HTML5 Canvas
     *
     * @method drawRecognitionResult
     * @param {AbstractComponent[]} components
     * @param {TextDocument} recognitionResult
     */
    InkRenderer.prototype.drawRecognitionResult = function (components, recognitionResult) {
        this.clear();
        if (recognitionResult) {
            this.drawComponents(components);
        } else {
            this.drawComponents(components);
        }
    }
        ;

    /**
     * Draw components
     *
     * @method drawComponents
     * @param {AbstractComponent[]} components
     */
    InkRenderer.prototype.drawComponents = function (components) {
        for (var i in components) {
            var component = components[i];
            //if (component instanceof scope.TextInputUnit) {
            //    this.drawComponents(component.getComponents());
            //} else 
            if (component instanceof scope.AbstractTextInputComponent) {
                _drawTextComponent(component, this.getContext(), this.getParameters());
            } else if (component instanceof scope.AbstractComponent) {
                scope.AbstractRenderer.prototype.drawComponent.call(this, component);
                // super
            } else {
                throw new Error('not implemented');
            }
        }
    }
        ;

    /**
     * Draw text component
     *
     * @private
     * @method _drawTextComponent
     * @param {AbstractTextInputComponent} component
     * @param {Object} context
     * @param {PenParameters} parameters
     */
    var _drawTextComponent = function (component, context, parameters) {
        if (component instanceof scope.StringInputComponent) {
            _drawString(component, context, parameters);
        } else if (component instanceof scope.CharInputComponent) {
            _drawChar(component, context, parameters);
        } else {
            throw new Error('Component not implemented: ' + component.getType());
        }
    };

    /**
     * Draw char
     *
     * @private
     * @method _drawChar
     * @param {CharInputComponent} char
     * @param {Object} context The canvas 2d context
     * @param {PenParameters} parameters
     */
    var _drawChar = function (component, context, parameters) {
        // jshint ignore:line
        throw new Error('not implemented');
    };

    /**
     * Draw string
     *
     * @private
     * @method _drawString
     * @param {StringInputComponent} string
     * @param {Object} context The canvas 2d context
     * @param {PenParameters} parameters
     */
    var _drawString = function (component, context, parameters) {
        // jshint ignore:line
        var opt = component.getOptions();
        context.fillText(opt.text, opt.x, opt.y);
        // throw new Error('not implemented');
    };

    // Export
    scope.InkRenderer = InkRenderer;
}
)(MyScript);

// InkPaper
(function (scope) {
    /**
     * InkPaper
     *
     * @class InkPaper
     * @param {Element} element
     * @param {Object} [options]
     * @param {Function} [callback] callback function
     * @param {Object} callback.data The recognition result
     * @param {Object} callback.err The err to the callback
     * @constructor
     */
    function InkPaper(element, options, callback) {
        this._element = element;
        this._instanceId = undefined;
        this._timerId = undefined;
        this._initialized = false;
        this._lastSentComponentIndex = 0;
        this._components = [];
        this._redoComponents = [];
        this.isStarted = false;
        this.resultCallback = callback;
        this.changeCallback = undefined;

        // Capture
        var tempCanvas = _createCanvas(element, 'temp-canvas');
        this.canvasRatio = _getCanvasRatio(tempCanvas);
        element.removeChild(tempCanvas);
        //this.canvasRatio = 1;

        // Rendering
        if (options.render_canvas !== false) {
            var canvas = _createCanvas(element, element.id + '-canvas');
        }
        this._renderingCanvas = _createCanvas(element, element.id + '-ink-canvas');
        this._captureCanvas = _createCanvas(element, element.id + '-capture-canvas');
        this._captureCanvas.style['position'] = 'absolute';
        this._renderingCanvas.style['position'] = 'absolute';
        this._captureCanvas.style['left'] = '0px';
        this._renderingCanvas.style['left'] = '0px';
        this._penable = true;
        this._penorerase = 'pen';
        this._currentpageNo = 0;
        this._pagelist = [];

        this._inkGrabber = new scope.InkGrabber(this._captureCanvas.getContext('2d'));//临时画布，绘制当前的正在写画的画布
        this._inkRenderer = new scope.InkRenderer(this._renderingCanvas.getContext('2d'));//保存历史画笔信息，
        gContext = this._inkRenderer;
        //this._textRenderer = new scope.TextRenderer(this._renderingCanvas.getContext('2d'));
        //this._mathRenderer = new scope.MathRenderer(this._renderingCanvas.getContext('2d'));
        //this._shapeRenderer = new scope.ShapeRenderer(this._renderingCanvas.getContext('2d'));
        //this._musicRenderer = new scope.MusicRenderer(this._renderingCanvas.getContext('2d'));
        //this._analyzerRenderer = new scope.AnalyzerRenderer(this._renderingCanvas.getContext('2d'));

        // Recognition
        //this._textRecognizer = new scope.TextRecognizer();
        //this._mathRecognizer = new scope.MathRecognizer();
        //this._shapeRecognizer = new scope.ShapeRecognizer();
        //this._musicRecognizer = new scope.MusicRecognizer();
        //this._analyzerRecognizer = new scope.AnalyzerRecognizer();

        //this._textWSRecognizer = new scope.TextWSRecognizer(this._handleMessage.bind(this));
        //this._mathWSRecognizer = new scope.MathWSRecognizer(this._handleMessage.bind(this));

        // this._attachListeners(element);

        this.options = {
            // Default options
            type: scope.RecognitionType.TEXT,
            protocol: scope.Protocol.REST,
            ssl: true,
            width: 400,
            height: 300,
            timeout: 2000,
            typeset: false,
            components: [],
            //textParameters: new scope.TextParameter(),
            //mathParameters: new scope.MathParameter(),
            //shapeParameters: new scope.ShapeParameter(),
            //musicParameters: new scope.MusicParameter(),
            //analyzerParameters: new scope.AnalyzerParameter()
        };

        if (options) {
            for (var idx in options) {
                if (options[idx] !== undefined) {
                    this.options[idx] = options[idx];
                    // Override current options
                }
            }
        }

        // Recognition type
        this.setType(this.options.type);

        //this.setHost(this.options.host);
        //this.setSSL(this.options.ssl);

        //this.setTextParameters(this.options.textParameters); // jshint ignore:line
        //this.setMathParameters(this.options.mathParameters); // jshint ignore:line
        //this.setShapeParameters(this.options.shapeParameters); // jshint ignore:line
        //this.setMusicParameters(this.options.musicParameters); // jshint ignore:line
        //this.setAnalyzerParameters(this.options.analyzerParameters); // jshint ignore:line

        //this.setProtocol(this.options.protocol);
        this.setTimeout(this.options.timeout);
        this.setApplicationKey(this.options.applicationKey);
        this.setHmacKey(this.options.hmacKey);

        this.setPenParameters(this.options.penParameters);

        // this.setPrecision(this.options.precision);
        // this.setTypeset(this.options.typeset);
        this.setComponents(this.options.components);

        this.setWidth(this.options.width);
        this.setHeight(this.options.height);
        element.style.width = this.options.width + 'px';
        element.style.height = this.options.height + 'px';
        canvas.width = this._renderingCanvas.width;
        canvas.height = this._renderingCanvas.height;

        this.setCanvasSmoothing(this._captureCanvas);
        this.setCanvasSmoothing(this._renderingCanvas);
    }

    InkPaper.prototype.setCanvasSmoothing = function (canvas) {
        var ctx = canvas.getContext('2d');

        ctx.imageSmoothingEnabled = ctx.imageSmoothingEnabled || ctx.webkitImageSmoothingEnabled || ctx.mozImageSmoothingEnabled || ctx.msImageSmoothingEnabled || ctx.oImageSmoothingEnabled;
        ctx.imageSmoothingEnabled = true;
    }

    InkPaper.prototype.getPageinfo = function (pageid) {

        var length = this._pagelist.length;
        for (var i = 0; i < length; i++) {
            var item = this._pagelist[i];
            if (item.getPageid() == pageid)
                return item;
        }
        return undefined;

    }
        ;

    /**
     * Set the width
     *
     * @method setWidth
     * @param {Number} width
     */

    InkPaper.prototype.setWidth = function (width) {
        if (width > 0) {
            this._captureCanvas.width = width * this.canvasRatio;
            this._captureCanvas.style.width = width + 'px';
            this._captureCanvas.getContext('2d').scale(this.canvasRatio, this.canvasRatio);

            this._renderingCanvas.width = width * this.canvasRatio;
            this._renderingCanvas.style.width = width + 'px';
            this._renderingCanvas.getContext('2d').scale(this.canvasRatio, this.canvasRatio);
        }
        this._initRenderingCanvas(true);
    }
        ;

    /**
     * Set the height
     *
     * @method setHeight
     * @param {Number} height
     */
    InkPaper.prototype.setHeight = function (height) {
        if (height > 0) {
            this._captureCanvas.height = height * this.canvasRatio;
            this._captureCanvas.style.height = height + 'px';
            this._captureCanvas.getContext('2d').scale(this.canvasRatio, this.canvasRatio);

            this._renderingCanvas.height = height * this.canvasRatio;
            this._renderingCanvas.style.height = height + 'px';

            this._renderingCanvas.getContext('2d').scale(this.canvasRatio, this.canvasRatio);
        }
        this._initRenderingCanvas(true);
    }
        ;

    /**
     * Set the network protocol (REST or WebSocket)
     *
     * @param {'REST'|'WebSocket'} protocol
     */
    InkPaper.prototype.setProtocol = function (protocol) {
        switch (protocol) {
            case scope.Protocol.REST:
                this._selectedRecognizer = this._selectedRESTRecognizer;
                break;
            case scope.Protocol.WS:
                this.setTimeout(-1);
                // FIXME hack to avoid border issues
                this._selectedRecognizer = this._selectedWSRecognizer;
                break;
            default:
                throw new Error('Unknown protocol: ' + protocol);
        }
        this._instanceId = undefined;
        this._initialized = false;
        this._lastSentComponentIndex = 0;
    }
        ;

    /**
     * Get the network protocol (REST or WebSocket)
     *
     * @returns {'REST'|'WebSocket'}
     */
    InkPaper.prototype.getProtocol = function () {
        if (this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
            return scope.Protocol.WS;
        } else {
            return scope.Protocol.REST;
        }
    }
        ;

    /**
     * Set recognition type
     *
     * @method setType
     * @param {'TEXT'|'MATH'|'SHAPE'|'MUSIC'|'ANALYZER'} type
     */
    InkPaper.prototype.setType = function (type) {
        switch (type) {
            case scope.RecognitionType.TEXT:
                this._selectedRenderer = this._textRenderer;
                this._selectedRESTRecognizer = this._textRecognizer;
                this._selectedWSRecognizer = this._textWSRecognizer;
                break;
            case scope.RecognitionType.MATH:
                this._selectedRenderer = this._mathRenderer;
                this._selectedRESTRecognizer = this._mathRecognizer;
                this._selectedWSRecognizer = this._mathWSRecognizer;
                break;
            case scope.RecognitionType.SHAPE:
                this._selectedRenderer = this._shapeRenderer;
                this._selectedRESTRecognizer = this._shapeRecognizer;
                break;
            case scope.RecognitionType.MUSIC:
                this._selectedRenderer = this._musicRenderer;
                this._selectedRESTRecognizer = this._musicRecognizer;
                break;
            case scope.RecognitionType.ANALYZER:
                this._selectedRenderer = this._analyzerRenderer;
                this._selectedRESTRecognizer = this._analyzerRecognizer;
                break;
            case scope.RecognitionType.INK:
                this._selectedRenderer = this._inkRenderer;
                break;
            default:
                throw new Error('Unknown type: ' + type);
        }
        this._instanceId = undefined;
        this._initialized = false;
        this._lastSentComponentIndex = 0;
    }
        ;

    /**
     * Get recognition type
     *
     * @method getType
     * @returns {'TEXT'|'MATH'|'SHAPE'|'MUSIC'|'ANALYZER'} type
     */
    InkPaper.prototype.getType = function () {
        if (this._selectedRenderer instanceof scope.TextRenderer) {
            return scope.RecognitionType.TEXT;
        }
        if (this._selectedRenderer instanceof scope.MathRenderer) {
            return scope.RecognitionType.MATH;
        }
        if (this._selectedRenderer instanceof scope.ShapeRenderer) {
            return scope.RecognitionType.SHAPE;
        }
        if (this._selectedRenderer instanceof scope.MusicRenderer) {
            return scope.RecognitionType.MUSIC;
        }
        if (this._selectedRenderer instanceof scope.AnalyzerRenderer) {
            return scope.RecognitionType.ANALYZER;
        }
        throw new Error('Unknown type');
    }
        ;

    /**
     * Get the recognition timeout
     *
     * @method getTimeout
     * @returns {Number}
     */
    InkPaper.prototype.getTimeout = function () {
        return this.timeout;
    }
        ;

    /**
     * Set the recognition timeout
     *
     * @method setTimeout
     * @param {Number} timeout
     */
    InkPaper.prototype.setTimeout = function (timeout) {
        this.timeout = timeout;
    }
        ;

    /**
     * Set the recognition precision
     *
     * @method setPrecision
     * @param {Number} precision
     */
    InkPaper.prototype.setPrecision = function (precision) {
        this._textRecognizer.setPrecision(precision);
        this._textWSRecognizer.setPrecision(precision);
        this._mathRecognizer.setPrecision(precision);
        this._mathWSRecognizer.setPrecision(precision);
        this._shapeRecognizer.setPrecision(precision);
        this._musicRecognizer.setPrecision(precision);
        this._analyzerRecognizer.setPrecision(precision);
    }
        ;

    /**
     * Get the default components
     *
     * @method getComponents
     * @return {Array} components
     */
    InkPaper.prototype.getComponents = function () {
        return this.options.components;
    }
        ;

    /**
     * Set the default components
     *
     * @method setComponents
     * @param {Array} components
     */
    InkPaper.prototype.setComponents = function (components) {
        this.options.components = components;
        this._initRenderingCanvas(true);
    }
        ;

    /**
     * Get the application key
     *
     * @method getApplicationKey
     * @returns {String}
     */
    InkPaper.prototype.getApplicationKey = function () {
        return this.applicationKey;
    }
        ;

    /**
     * Set the application key
     *
     * @method setApplicationKey
     * @param {String} applicationKey
     */
    InkPaper.prototype.setApplicationKey = function (applicationKey) {
        this.applicationKey = applicationKey;
    }
        ;

    /**
     * Get the HMAC key
     *
     * @method getHmacKey
     * @returns {String}
     */
    InkPaper.prototype.getHmacKey = function () {
        return this.hmacKey;
    }
        ;

    /**
     * Set the HMAC key
     *
     * @method setHmacKey
     * @param {String} hmacKey
     */
    InkPaper.prototype.setHmacKey = function (hmacKey) {
        this.hmacKey = hmacKey;
    }
        ;

    /**
     * Set text recognition parameters
     *
     * @method setTextParameters
     * @param {TextParameter} textParameters
     */
    InkPaper.prototype.setTextParameters = function (textParameters) {
        if (textParameters) {
            if (this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
                this.isStarted = false;
                this._selectedRecognizer.resetWSRecognition();
            }
            for (var i in textParameters) {
                if (textParameters[i] !== undefined) {
                    this._textRecognizer.getParameters()[i] = textParameters[i];
                    // Override options
                    this._textWSRecognizer.getParameters()[i] = textParameters[i];
                    // Override options
                    this._analyzerRecognizer.getParameters().getTextParameters()[i] = textParameters[i];
                    // Override options
                }
            }
        }
    }
        ;

    /**
     * Get text recognition parameters
     *
     * @method getTextParameters
     * @returns {TextParameter} textParameters
     */
    InkPaper.prototype.getTextParameters = function () {
        return this._textRecognizer.getParameters();
    }
        ;

    /**
     * Set math recognition parameters
     *
     * @method setMathParameters
     * @param {MathParameter} mathParameters
     */
    InkPaper.prototype.setMathParameters = function (mathParameters) {
        if (mathParameters) {
            if (this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
                this.isStarted = false;
                this._selectedRecognizer.resetWSRecognition();
            }
            for (var i in mathParameters) {
                if (mathParameters[i] !== undefined) {
                    this._mathRecognizer.getParameters()[i] = mathParameters[i];
                    // Override options
                    this._mathWSRecognizer.getParameters()[i] = mathParameters[i];
                    // Override options
                }
            }
        }
    }
        ;

    /**
     * Get math recognition parameters
     *
     * @method getMathParameters
     * @returns {MathParameter} mathParameters
     */
    InkPaper.prototype.getMathParameters = function () {
        return this._mathRecognizer.getParameters();
    }
        ;

    /**
     * Set shape recognition parameters
     *
     * @method setShapeParameters
     * @param {ShapeParameter} shapeParameters
     */
    InkPaper.prototype.setShapeParameters = function (shapeParameters) {
        if (shapeParameters) {
            if (this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
                this.isStarted = false;
                this._selectedRecognizer.resetWSRecognition();
            }
            for (var i in shapeParameters) {
                if (shapeParameters[i] !== undefined) {
                    this._shapeRecognizer.getParameters()[i] = shapeParameters[i];
                    // Override options
                }
            }
        }
    }
        ;

    /**
     * Get shape recognition parameters
     *
     * @method getShapeParameters
     * @returns {ShapeParameter} shapeParameters
     */
    InkPaper.prototype.getShapeParameters = function () {
        return this._shapeRecognizer.getParameters();
    }
        ;

    /**
     * Set music recognition parameters
     *
     * @method setMusicParameters
     * @param {MusicParameter} musicParameters
     */
    InkPaper.prototype.setMusicParameters = function (musicParameters) {
        if (musicParameters) {
            if (this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
                this.isStarted = false;
                this._selectedRecognizer.resetWSRecognition();
            }
            for (var i in musicParameters) {
                if (musicParameters[i] !== undefined) {
                    this._musicRecognizer.getParameters()[i] = musicParameters[i];
                    // Override options
                }
            }
            this._initRenderingCanvas(true);
        }
    }
        ;

    /**
     * Get music recognition parameters
     *
     * @method getMusicParameters
     * @returns {MusicParameter} musicParameters
     */
    InkPaper.prototype.getMusicParameters = function () {
        return this._musicRecognizer.getParameters();
    }
        ;

    /**
     * Set analyzer recognition parameters
     *
     * @method setAnalyzerParameters
     * @param {AnalyzerParameter} analyzerParameters
     */
    InkPaper.prototype.setAnalyzerParameters = function (analyzerParameters) {
        if (analyzerParameters) {
            if (this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
                this.isStarted = false;
                this._selectedRecognizer.resetWSRecognition();
            }
            for (var i in analyzerParameters) {
                if (analyzerParameters[i] !== undefined) {
                    this._analyzerRecognizer.getParameters()[i] = analyzerParameters[i];
                    // Override options
                }
            }
        }
    }
        ;

    /**
     * Get analyzer recognition parameters
     *
     * @method getAnalyzerParameters
     * @returns {AnalyzerParameter} analyzerParameters
     */
    InkPaper.prototype.getAnalyzerParameters = function () {
        return this._analyzerRecognizer.getParameters();
    }
        ;

    /**
     * Set pen parameters
     *
     * @method setPenParameters
     * @param {PenParameters} penParameters
     */
    InkPaper.prototype.setPenParameters = function (penParameters) {
        if (penParameters) {
            for (var i in penParameters) {
                if (penParameters[i] !== undefined) {
                    this._selectedRenderer.getParameters()[i] = penParameters[i];
                    // Override options
                }
            }
            var params = this._selectedRenderer.getParameters();
            this._inkGrabber.setParameters(params);
            // Override options
            this._textRenderer.setParameters(params);
            // Override options
            this._mathRenderer.setParameters(params);
            // Override options
            this._shapeRenderer.setParameters(params);
            // Override options
            this._musicRenderer.setParameters(params);
            // Override options
            this._analyzerRenderer.setParameters(params);
            // Override options
        }
    }
        ;

    /**
     * Get pen parameters
     *
     * @method getPenParameters
     * @returns {PenParameters} penParameters
     */
    InkPaper.prototype.getPenParameters = function () {
        return this._selectedRenderer.getParameters();
    }
        ;

    /**
     * Enable / disable typeset
     *
     * @method setTypeset
     * @param {Boolean} typeset
     */
    InkPaper.prototype.setTypeset = function (typeset) {
        this._textRenderer.setTypeset(typeset);
        this._mathRenderer.setTypeset(typeset);
        this._shapeRenderer.setTypeset(typeset);
        this._musicRenderer.setTypeset(typeset);
        this._analyzerRenderer.setTypeset(typeset);
    }
        ;

    /**
     * Get available languages
     *
     * @method getAvailableLanguages
     * @param {String} [inputMode] input mode
     */
    InkPaper.prototype.getAvailableLanguages = function (inputMode) {
        if (this._selectedRESTRecognizer != undefined) {
            this._selectedRESTRecognizer.getAvailableLanguageList(this.getApplicationKey(), inputMode ? inputMode : this._textRecognizer.getParameters().getInputMode()).then(function (data) {
                this._onResult(data);
            }
                .bind(this), function (error) {
                    this._onResult(undefined, error);
                }
                    .bind(this));
        }
    }
        ;

    /**
     * Get the renderer
     *
     * @method getRenderer
     * @returns {AbstractRenderer}
     */
    InkPaper.prototype.getRenderer = function () {
        return this._selectedRenderer;
    }
        ;

    /**
     * Get the ink capturer
     *
     * @method getInkGrabber
     * @returns {InkGrabber}
     */
    InkPaper.prototype.getInkGrabber = function () {
        return this._inkGrabber;
    }
        ;

    /**
     * Get the recognizer
     *
     * @method getRecognizer
     * @returns {AbstractRecognizer}
     */
    InkPaper.prototype.getRecognizer = function () {
        return this._selectedRecognizer;
    }
        ;

    /**
     * Set the change callback
     *
     * @method setChangeCallback
     * @param {Function} callback callback function
     * @param {Object} callback.data The inkPaper state
     */
    InkPaper.prototype.setChangeCallback = function (changeCallback) {
        this.changeCallback = changeCallback;
    }
        ;

    InkPaper.prototype.setPenable = function (value) {
        this._penable = value;
    }
        ;

    InkPaper.prototype.isPenable = function () {
        return this._penable;
    }
        ;

    InkPaper.prototype.setPenOrErase = function (value) {
        this._penorerase = value;
        this._inkGrabber.setPenOrErase(value);
    }
        ;

    InkPaper.prototype.setPenColor = function (value) {
        this._inkGrabber.setPenColor(value);
    }
        ;

    InkPaper.prototype.setPenWidth = function (value) {
        this._inkGrabber.setPenWidth(value);
    }
        ;

    InkPaper.prototype.getPenOrErase = function () {
        return this._penorerase;
    }
        ;

    InkPaper.prototype.setCurrentPageNo = function (value) {
        console.log("InkPaper setCurrentPageNo,pageNo=" + value);
        this._currentpageNo = value;
        this._inkGrabber.setcurrPageNo(value);
    }
        ;

    InkPaper.prototype.addNewPage = function (pageid) {
        console.log("InkPaper addNewPage,pageid=" + pageid);
        var pageCom = new scope.PageComponent(pageid);
        this._pagelist.push(pageCom);
        //this._currentpageNo = value;
        //this._inkGrabber.setcurrPageNo(value);
    }
        ;

    InkPaper.prototype.getCurrentPageNo = function () {
        return this._currentpageNo;
    }
        ;

    /**
     * Set the recognition result callback
     *
     * @method setResultCallback
     * @param {Function} callback callback function
     * @param {Object} callback.data The recognition result
     */
    InkPaper.prototype.setResultCallback = function (callback) {
        this.resultCallback = callback;
    }
        ;

    /**
     * Recognize
     *
     * @method recognize
     * @returns {Promise}
     */
    InkPaper.prototype.recognize = function () {
        var input = this.getComponents().concat(this._components);
        if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
            if (this._initialized) {
                var lastInput = input.slice(this._lastSentComponentIndex);

                if (lastInput.length > 0) {
                    this._lastSentComponentIndex = input.length;
                    if (!this.isStarted) {
                        this.isStarted = true;
                        this._selectedRecognizer.startWSRecognition(lastInput);
                    } else {
                        this._selectedRecognizer.continueWSRecognition(lastInput, this._instanceId);
                    }
                } else {
                    this._renderResult();
                }
            }
        } else {
            if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.ShapeRecognizer) {
                this._instanceId = undefined;
            }

            if (input.length > 0) {
                if (!this.isStarted) {
                    this._startRESTRecognition(input);
                } else {
                    this._continueRESTRecognition(input, this._instanceId);
                }
            } else {
                this._renderResult();
            }
        }
    }
        ;

    InkPaper.prototype._startRESTRecognition = function (components) {

        this._instanceId = undefined;
        if (this._selectedRecognizer != undefined) {
            this._selectedRecognizer.doSimpleRecognition(this.getApplicationKey(), this._instanceId, components, this.getHmacKey()).then(function (data) {
                if (!this.isStarted) {
                    this.isStarted = true;
                    this._lastSentComponentIndex = components.length;
                    this._instanceId = data.getInstanceId();
                    this._renderResult(data);
                }
            }
                .bind(this), function (error) {
                    this._onResult(undefined, error);
                }
                    .bind(this));
        }
    }
        ;

    InkPaper.prototype._continueRESTRecognition = function (components, instanceId) {

        this._selectedRecognizer.doSimpleRecognition(this.getApplicationKey(), instanceId, components, this.getHmacKey()).then(function (data) {
            this._lastSentComponentIndex = this._lastSentComponentIndex + components.length;
            this._renderResult(data);
        }
            .bind(this), function (error) {
                this._onResult(undefined, error);
            }
                .bind(this));
    }
        ;

    InkPaper.prototype._clearRESTRecognition = function (instanceId) {

        if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.ShapeRecognizer) {
            this.isStarted = false;
            this._lastSentComponentIndex = 0;
            this._selectedRecognizer.clearShapeRecognitionSession(this.getApplicationKey(), instanceId).then(function (data) {
                this._instanceId = undefined;
                this._onResult(data);
            }
                .bind(this), function (error) {
                    this._onResult(undefined, error);
                }
                    .bind(this));
        } else {
            this._onResult();
        }
    }
        ;

    /**
     * Return true if you can undo
     *
     * @method canUndo
     * @returns {Boolean}
     */
    InkPaper.prototype.canUndo = function () {
        var pageinfo = this.getPageinfo(this._currentpageNo);
        if (pageinfo == "")
            return false;
        return pageinfo.getStroke().length > 0;
        //return this._components.length > 0;
    }
        ;

    /**
     * Undo
     *
     * @method undo
     */
    InkPaper.prototype.undo = function (clear_canvas) {
        if (this.canUndo()) {

            var pageinfo = this.getPageinfo(this._currentpageNo);
            if (pageinfo == "")
                return false;
            var _strokecomponents = pageinfo.getStroke();

            //Remove the scratched state for Math strokes
            _strokecomponents.forEach(function (stroke) {
                stroke.scratchedStroke = false;
            });
            //Remove the latsModel used for Shape

            pageinfo.redo(pageinfo.popStroke());
            this.updatedModel = undefined;
            //this._redoComponents.push(this._components.pop());

            this._clearRESTRecognition(this._instanceId);

            this._initRenderingCanvas(true);
            this._onChange();

            this.isStarted = false;
            if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
                this._selectedRecognizer.resetWSRecognition();
            } else {
                clearTimeout(this._timerId);
                if (this.getTimeout() > -1) {
                    this._timerId = setTimeout(this.recognize.bind(this), this.getTimeout());
                } else {
                    this._onResult();
                }
            }
        }
    }
        ;

    /**
     * Return true if you can redo
     *
     * @method canRedo
     * @returns {Boolean}
     */
    InkPaper.prototype.canRedo = function () {
        var pageinfo = this.getPageinfo(this._currentpageNo);
        if (pageinfo == "")
            return false;
        return pageinfo.getredoStroke().length > 0;
    }
        ;

    /**
     * Redo
     *
     * @method redo
     */
    InkPaper.prototype.redo = function (clear_canvas) {
        if (this.canRedo()) {
            //this._components.push(this._redoComponents.pop());
            var pageinfo = this.getPageinfo(this._currentpageNo);
            if (pageinfo == "")
                return false;
            pageinfo.push(pageinfo.popredoStroke());

            this._clearRESTRecognition(this._instanceId);

            this._initRenderingCanvas(true);
            this._onChange();

            if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
                this.recognize();
            } else {
                clearTimeout(this._timerId);
                this.isStarted = false;
                if (this.getTimeout() > -1) {
                    this._timerId = setTimeout(this.recognize.bind(this), this.getTimeout());
                } else {
                    this._onResult();
                }
            }
        }
    }
        ;

    InkPaper.prototype.renderAll = function (clear_canvas) {
        if (this._selectedRenderer != null) {
            if (clear_canvas)
                this._selectedRenderer.clear();
            var pageinfo = this.getPageinfo(this._currentpageNo);
            if (pageinfo == "")
                return;
            var _initcomponents = pageinfo.getStroke();
            this._selectedRenderer.drawComponents(this.getComponents().concat(_initcomponents));
        }
    }
        ;

    /**
     * Clear the ink paper
     *
     * @method clear
     */
    InkPaper.prototype.clear = function () {
        var pageinfo = this.getPageinfo(this._currentpageNo);
        if (pageinfo == "")
            return;
        pageinfo.clear();

        this._clearRESTRecognition(this._instanceId);

        this._initRenderingCanvas(true);
        this._onChange();

        if (this._selectedRecognizer != null && this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
            this.isStarted = false;
            this._selectedRecognizer.resetWSRecognition();
        } else if (this._selectedRecognizer != null && this._selectedRecognizer instanceof scope.MusicRecognizer) {
            clearTimeout(this._timerId);
            this._onResult();
        } else {
            clearTimeout(this._timerId);
            if (this.getTimeout() > -1) {
                this._timerId = setTimeout(this.recognize.bind(this), this.getTimeout());
            } else {
                this._onResult();
            }
        }
    }
        ;

    InkPaper.event = {
        'addDomListener': function (element, useCapture, myfunction) {
            element.addEventListener(useCapture, myfunction);
        }
    };

    /**
     *
     * @private
     * @method _down
     * @param {Number} x X coordinate
     * @param {Number} y Y coordinate
     * @param {Date} [t] timeStamp
     */
    InkPaper.prototype._down = function (x, y, t) {
        clearTimeout(this._timerId);
        var sizeChanged = false;
        if (this._captureCanvas.clientHeight * this.canvasRatio !== this._captureCanvas.height) {
            this._captureCanvas.height = this._captureCanvas.clientHeight * this.canvasRatio;
            this._renderingCanvas.height = this._renderingCanvas.clientHeight * this.canvasRatio;
            sizeChanged = true;
        }

        if (this._captureCanvas.clientWidth * this.canvasRatio !== this._captureCanvas.width) {
            this._captureCanvas.width = this._captureCanvas.clientWidth * this.canvasRatio;
            this._renderingCanvas.width = this._renderingCanvas.clientWidth * this.canvasRatio;
            sizeChanged = true;
        }

        //Safari trash the canvas content when heigth or width are modified.
        if (sizeChanged) {
            this._captureCanvas.getContext('2d').scale(this.canvasRatio, this.canvasRatio);
            this._renderingCanvas.getContext('2d').scale(this.canvasRatio, this.canvasRatio);
            this._initRenderingCanvas(true);
        }

        if (this.canRedo()) {
            this._redoComponents = [];
            this._onChange();
        }

        this._inkGrabber.startCapture(x, y, t);
    }
        ;

    /**
     *
     * @private
     * @method _move
     * @param {Number} x X coordinate
     * @param {Number} y Y coordinate
     * @param {Date} [t] timeStamp
     */

    InkPaper.prototype._drawLine = function (sx, sy, ex, ey) {
        this._inkGrabber.drawLine(sx, sy, ex, ey);
    }
        ;

    InkPaper.prototype._drawInk = function (x, y, t) {
        this._inkGrabber.drawInk(x, y, t);
    }
        ;

    InkPaper.prototype._move = function (x, y, t) {
        return this._inkGrabber.continueCapture(x, y, t);
    }
        ;

    /**
     *鼠标抬起，清空临时画布，重新绘制到render画布层
     *双缓存
     * @private
     * @method _move
     * @param {Number} x X coordinate
     * @param {Number} y Y coordinate
     * @param {Date} [t] timeStamp
     */
    InkPaper.prototype._up = function (x, y, t) {
        console.log("鼠标抬起，清空画布，重新绘制");
        resetLastInfo();
        this._inkGrabber.endCapture(x, y, t);

        var stroke = this._inkGrabber.getStroke();
        var shape = this._inkGrabber.getShape();
        this._inkGrabber.clear();//清空临时画布 #div-rendering-capture-canvas
        test = this._inkGrabber;
        if (stroke.getLength() > 0) {
            if (this._selectedRenderer != undefined) {
                this._selectedRenderer.drawComponent(stroke, true);//将当前笔画绘制到render层上#div-rendering-ink-canvas
            }
            var pageinfo = this.getPageinfo(this._currentpageNo);
            if (pageinfo)
                pageinfo.push(stroke);
        }

        //以下其他
        else if (shape && !shape.isEmpty()) {
            if (this._selectedRenderer != undefined) {
                this._selectedRenderer.drawComponent(shape);
            }
            var pageinfo = this.getPageinfo(this._currentpageNo);
            if (pageinfo)
                pageinfo.push(shape);
        }

        this._onChange();

        if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.AbstractWSRecognizer) {
            if (!this._selectedRecognizer.isOpen() && !this._selectedRecognizer.isConnecting()) {
                this._selectedRecognizer.open();
            } else {
                this.recognize();
            }
        } else {
            clearTimeout(this._timerId);
            if (this.getTimeout() > -1) {
                this._timerId = setTimeout(this.recognize.bind(this), this.getTimeout());
            }
        }
        resetLastInfo();
    }
        ;

    InkPaper.prototype._onResult = function (data, err) {
        if (this.resultCallback) {
            this.resultCallback(data, err);
        }
        if (err) {
            this._element.dispatchEvent(new CustomEvent('error', {
                detail: err
            }));
        } else {
            this._element.dispatchEvent(new CustomEvent('success', {
                detail: data
            }));
        }
    }
        ;

    InkPaper.prototype._onChange = function () {
        var data = {
            canUndo: this.canUndo(),
            undoLength: this._components.length,
            canRedo: this.canRedo(),
            redoLength: this._redoComponents.length
        };

        if (this.changeCallback) {
            this.changeCallback(data)
        }
        this._element.dispatchEvent(new CustomEvent('changed', {
            detail: data
        }));
    }
        ;

    InkPaper.prototype._renderResult = function (data) {
        if (!(this._selectedRenderer instanceof scope.InkRenderer)) {
            this.updatedModel = this._selectedRenderer.drawRecognitionResult(this.getComponents().concat(this._components), data ? data.getDocument() : undefined);
        }
        if (this._selectedRecognizer != null && this._selectedRecognizer instanceof scope.MusicRecognizer) {
            if (this._selectedRecognizer.getParameters().getStaff() instanceof scope.MusicStaff) {
                this._selectedRenderer.drawStaff(this._selectedRecognizer.getParameters().getStaff());
            }
        }
        this._onResult(data);
        return data;
    }
        ;

    /**
     * Set recognition service url
     *
     * @param {String} host
     */
    InkPaper.prototype.setHost = function (host) {
        this._textRecognizer.setHost(host);
        this._textWSRecognizer.setHost(host);
        this._mathRecognizer.setHost(host);
        this._mathWSRecognizer.setHost(host);
        this._shapeRecognizer.setHost(host);
        this._musicRecognizer.setHost(host);
        this._analyzerRecognizer.setHost(host);
    }
        ;

    /**
     * @private
     */
    InkPaper.prototype.setSSL = function (ssl) {
        this._textRecognizer.setSSL(ssl);
        this._textWSRecognizer.setSSL(ssl);
        this._mathRecognizer.setSSL(ssl);
        this._mathWSRecognizer.setSSL(ssl);
        this._shapeRecognizer.setSSL(ssl);
        this._musicRecognizer.setSSL(ssl);
        this._analyzerRecognizer.setSSL(ssl);
    }
        ;

    InkPaper.prototype.isWriting = function () {
        return this._inkGrabber.isWriting();
    }

    InkPaper.prototype.onPointerDown = function (e) {
        console.log("onPointerDown");
        var element = this._element;
        e.pointerId = e.pointerId | 1;
        if (!this.pointerId) {
            this.pointerId = e.pointerId;
            if (!this.isPenable())
                return;

            // e.preventDefault();

            var coord = _getCoordinates(e, element);
            this._down(coord.x, coord.y, coord.t);
        }
    }

    InkPaper.prototype.clearCurrent = function () {
        console.log("clearCurrent");
        this._inkGrabber.clearCurrent();
    }

    InkPaper.prototype.DrawLine = function (e, x, y, angle) {
        console.log("DrawLine");
        var element = this._element;
        e.pointerId = e.pointerId | 1;
        if (this.pointerId === e.pointerId) {
            if (!this.isPenable())
                return;

            var point = this._inkGrabber.getStroke().getPointByIndex(0);
            var coord = _getCoordinates(e, element);
            if (!point)
                return;

            if (x == -1 && y == -1) {
                this._drawLine(point.x, point.y, coord.x, coord.y);
            } else {
                point.y = (point.x - x) * Math.tan(angle) + y;
                coord.y = (coord.x - x) * Math.tan(angle) + y;
                this._drawLine(point.x, point.y, coord.x, coord.y);
            }
        }
    }

    InkPaper.prototype.DrawInk = function (e) {

        var element = this._element;
        e.pointerId = e.pointerId | 1;
        if (this.pointerId === e.pointerId) {
            if (!this.isPenable())
                return;

            var coord = _getCoordinates(e, element);
            this._drawInk(coord.x, coord.y, coord.t);
        }
    }

    InkPaper.prototype.onPointerMove = function (e) {
        console.log("onPointerMove");
        var element = this._element;
        e.pointerId = e.pointerId | 1;
        if (this.pointerId === e.pointerId) {
            if (!this.isPenable())
                return;

            // e.preventDefault();

            var coord = _getCoordinates(e, element);
            return this._move(coord.x, coord.y, coord.t);
        }
        return false;
    }

    InkPaper.prototype.onPointerUp = function (e) {
        console.log("onPointerUp");
        var element = this._element;
        e.pointerId = e.pointerId | 1;
        if (this.pointerId === e.pointerId) {
            if (!this.isPenable()) {
                this.pointerId = undefined;
                return;
            }

            // e.preventDefault();

            var coord = _getCoordinates(e, element);
            this._up(coord.x, coord.y, coord.t);

            this.pointerId = undefined;
        }
    }

    InkPaper.prototype.onPointerLeave = function (e) {
        console.log("onPointerLeave");
        var element = this._element;
        e.pointerId = e.pointerId | 1;
        if (this.pointerId === e.pointerId) {
            if (!this.isPenable()) {
                this.pointerId = undefined;
                return;
            }

            // e.preventDefault();

            var point = this._inkGrabber.getStroke().getPointByIndex(this._inkGrabber.getStroke().getLastIndexPoint());
            if (point !== undefined) {
                this._up(point.x, point.y, point.t);
            }
            this.pointerId = undefined;
        }
    }

    InkPaper.prototype.onPointerOut = function (e) {
        console.log("onPointerOut");
        var element = this._element;
        e.pointerId = e.pointerId | 1;
        if (this.pointerId === e.pointerId) {
            if (!this.isPenable()) {
                this.pointerId = undefined;
                return;
            }

            // e.preventDefault();

            var point = this._inkGrabber.getStroke().getPointByIndex(this._inkGrabber.getStroke().getLastIndexPoint());
            if (point !== undefined) {
                this._up(point.x, point.y, point.t);
            }
            this.pointerId = undefined;
        }
    }
    /**
     * Tool to attach touch events
     *
     * @private
     * @param {Element} element
     */
    InkPaper.prototype._attachListeners = function (element) {
        var self = this;

        //Desactivation of contextmenu to prevent safari to fire pointerdown only once
        element.addEventListener("contextmenu", function (e) {
            e.preventDefault();
            e.stopPropagation();
            return false;
        });

        element.addEventListener('pointerdown', function (e) {
            self.onPointerDown(e);
        }, false);

        element.addEventListener('pointermove', function (e) {
            self.onPointerMove(e);
        }, false);

        element.addEventListener('pointerup', function (e) {
            self.onPointerUp(e);
        }, false);

        element.addEventListener('pointerleave', function (e) {
            self.onPointerLeave(e);
        }, false);

        element.addEventListener('pointerout', function (e) {
            self.onPointerOut(e);
        }, false);
    }
        ;

    InkPaper.prototype._initRenderingCanvas = function (clear_canvas) {
        if (clear_canvas && this._selectedRenderer != undefined)
            this._selectedRenderer.clear();

        if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.MusicRecognizer) {
            if (this._selectedRecognizer.getParameters().getStaff() instanceof scope.MusicStaff) {
                this._selectedRenderer.drawStaff(this._selectedRecognizer.getParameters().getStaff());
            }
        }
        if (this._selectedRecognizer != undefined && this._selectedRecognizer instanceof scope.ShapeRecognizer && this.updatedModel) {
            this._selectedRenderer.drawRecognitionResult(this.updatedModel.components, this.updatedModel.document);
        } else if (this._selectedRenderer != undefined) {
            var pageinfo = this.getPageinfo(this._currentpageNo);
            if (!pageinfo)
                return;

            var _initcomponents = pageinfo.getStroke();
            this._selectedRenderer.drawComponents(this.getComponents().concat(_initcomponents));
        }
    }
        ;

    /**
     *
     * @param message
     * @param error
     * @returns {boolean} false no immediate replay needed, true when the call need to be replay ASAP
     * @private
     */
    InkPaper.prototype._handleMessage = function (message, error) {
        var replayNeeded = false;
        if (error) {
            replayNeeded = true;
            this._instanceId = undefined;
            this.isStarted = false;
            this._lastSentComponentIndex = 0;
            this._onResult(undefined, error);
        }

        if (message) {
            switch (message.type) {
                case 'open':
                    this._selectedWSRecognizer.initWSRecognition(this.getApplicationKey());
                    break;
                case 'hmacChallenge':
                    this._selectedWSRecognizer.takeUpHmacChallenge(this.getApplicationKey(), message.getChallenge(), this.getHmacKey());
                    break;
                case 'init':
                    this.isStarted = false;
                    this._initialized = true;
                    this._instanceId = undefined;
                    this._lastSentComponentIndex = 0;
                    this.recognize();
                    break;
                case 'reset':
                    this.isStarted = false;
                    this._instanceId = undefined;
                    this._lastSentComponentIndex = 0;
                    this.recognize();
                    break;
                case 'close':
                    this._initialized = false;
                    this._instanceId = undefined;
                    this._lastSentComponentIndex = 0;
                    break;
                default:
                    this.isStarted = true;
                    if (!this._instanceId) {
                        this._instanceId = message.getInstanceId();
                    }
                    this._renderResult(message);
                    break;
            }
        }
        return replayNeeded;
    }
        ;

    /**
     * Return the stats allowing to monitor what ink size is send to the server.
     * @returns Stats objects format {strokesCount : 0, pointsCount : 0, byteSize : 0, humanSize : 0, humanUnit : 'BYTE'} humanUnit could have the values BYTE, BYTES, KiB, MiB
     */
    InkPaper.prototype.getStats = function () {
        var stats = {
            strokesCount: 0,
            pointsCount: 0,
            byteSize: 0,
            humanSize: 0,
            humanUnit: 'BYTE'
        };
        if (this._components) {
            stats.strokesCount = this._components.length;
            var pointsCount = 0;
            for (var strokeNb = 0; strokeNb < this._components.length; strokeNb++) {
                pointsCount = pointsCount + this._components[strokeNb].x.length;
            }
            stats.strokesCount = this._components.length;
            stats.pointsCount = pointsCount;
            //We start with 270 as it is the size in bytes. Make a real computation implies to recode a doRecogntion
            var byteSize = 270;
            byteSize = JSON.stringify(this._components).length;
            stats.byteSize = byteSize;
            if (byteSize < 270) {
                stats.humanUnit = 'BYTE';
                stats.byteSize = 0;
                stats.humanSize = 0;
            } else if (byteSize < 2048) {
                stats.humanUnit = 'BYTES';
                stats.humanSize = byteSize;
            } else if (byteSize < 1024 * 1024) {
                stats.humanUnit = 'KiB';
                stats.humanSize = (byteSize / 1024).toFixed(2);
            } else {
                stats.humanUnit = 'MiB';
                stats.humanSize = (byteSize / 1024 / 1024).toFixed(2);
            }
        }
        return stats;
    }
        ;

    /**
     *
     * @param marginX the horizontal margin to apply (by default 10)
     * @param marginY the vertical margin to apply (by default 10)
     * @returns {ImageData} Build an ImageData object with content shrink to border of strokes.
     * @private
     */
    InkPaper.prototype.getInkAsImageData = function (marginX, marginY) {
        //Remove the scratched strokes
        var componentCopy = [];
        this._components.forEach(function (stroke) {
            if (stroke.scratchedStroke !== true) {
                componentCopy.push(stroke);
            }
        });

        if (!marginX) {
            marginX = 10;
        }
        if (!marginY) {
            marginY = 10;
        }

        if (componentCopy && componentCopy.length > 0) {
            var updatedStrokes;
            var strokesCount = componentCopy.length;
            //Initializing min and max
            var minX = componentCopy[0].x[0];
            var maxX = componentCopy[0].x[0];
            var minY = componentCopy[0].y[0];
            var maxY = componentCopy[0].y[0];

            // Computing the min and max for x and y
            for (var strokeNb = 0; strokeNb < componentCopy.length; strokeNb++) {
                var pointCount = componentCopy[strokeNb].x.length;
                for (var pointNb = 0; pointNb < pointCount; pointNb++) {
                    var currentX = componentCopy[strokeNb].x[pointNb];
                    var currentY = componentCopy[strokeNb].y[pointNb];
                    if (currentX < minX) {
                        minX = currentX;
                    }
                    if (currentX > maxX) {
                        maxX = currentX;
                    }
                    if (currentY < minY) {
                        minY = currentY;
                    }
                    if (currentY > maxY) {
                        maxY = currentY;
                    }
                }
            }
            var nonDisplayCanvas = document.createElement('canvas');
            nonDisplayCanvas.width = (maxX) + (2 * marginX);
            nonDisplayCanvas.height = (maxY) + (2 * marginY)

            var ctx = nonDisplayCanvas.getContext("2d");

            var imageRendered = new scope.ImageRenderer(ctx);
            imageRendered.drawComponents(componentCopy, ctx);

            // https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/getImageData
            return ctx.getImageData(minX - marginX, minY - marginY, (maxX - minX) + (2 * marginX), (maxY - minY) + (2 * marginY));
        }
    }
        ;

    /**
     *
     * @param marginX the horizontal margin to apply (by default 10)
     * @param marginY the vertical margin to apply (by default 10)
     * @returns {String} Build an String containg dataUrl with content shrink to border of strokes.
     * @private
     */
    InkPaper.prototype.getInkAsPng = function (marginX, marginY) {
        var imageRenderingCanvas = document.createElement('canvas');
        imageRenderingCanvas.style.display = 'none';

        var imageDataToRender = this.getInkAsImageData();
        imageRenderingCanvas.width = imageDataToRender.width;
        imageRenderingCanvas.style.width = imageDataToRender.width + 'px';
        imageRenderingCanvas.height = imageDataToRender.height;
        imageRenderingCanvas.style.height = imageDataToRender.height + 'px';
        var ctx = imageRenderingCanvas.getContext('2d');
        ctx.putImageData(imageDataToRender, 0, 0);
        return imageRenderingCanvas.toDataURL("image/png");
    }
        ;

    /**
     * Tool to create canvas
     *
     * @private
     * @param {Element} parent
     * @param {String} id
     * @returns {Element}
     */
    function _createCanvas(parent, id) {
        var el = document.getElementById(id);
        if (el && el.getContext) {
            return el;
        } else {
            var count = document.querySelectorAll('canvas[id^=' + id + ']').length;
            var canvas = document.createElement('canvas');
            if (el)
                canvas.id = id + '-' + count;
            else
                canvas.id = id;
            parent.appendChild(canvas);
        }
        return canvas;
    }

    /**
     * Tool to get canvas ratio (retina display)
     *
     * @private
     * @param {Element} canvas
     * @returns {Number}
     */
    function _getCanvasRatio(canvas) {
        if (canvas) {
            var context = canvas.getContext('2d')
                , devicePixelRatio = window.devicePixelRatio || 1
                , backingStoreRatio = context.webkitBackingStorePixelRatio || context.mozBackingStorePixelRatio || context.msBackingStorePixelRatio || context.oBackingStorePixelRatio || context.backingStorePixelRatio || 1;
            return devicePixelRatio / backingStoreRatio;
        }
        return 1;
    }

    /**
     * Tool to get proper coordinates
     *
     * @private
     * @param {Event} e
     * @param {Element} element
     * @returns {Object}
     */
    function _getCoordinates(e, container) {
        if (e.changedTouches)
            e = e.changedTouches[0];
        var rect = container.getBoundingClientRect();
        return {
            x: e.clientX - rect.left - container.clientLeft,
            y: e.clientY - rect.top - container.clientTop,
            t: e.timeStamp
        };//返回时间毫秒数，相对值即可
    }

    // Export
    scope.InkPaper = InkPaper;
}
)(MyScript);
