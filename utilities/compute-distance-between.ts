import LatLng = google.maps.LatLng;

// Methods taken from https://github.com/NotWoods/spherical-geometry-js

const EARTH_RADIUS = 6378137;

function toRadians(angleDegrees: number) {
    return (angleDegrees * Math.PI) / 180.0;
}

function computeDistanceBetweenHelper(from: LatLng, to: any) {
    const radFromLat = toRadians(from.lat());
    const radFromLng = toRadians(from.lng());
    const radToLat = toRadians(to.lat);
    const radToLng = toRadians(to.lng);
    return (
        2 *
        Math.asin(
            Math.sqrt(
                Math.pow(Math.sin((radFromLat - radToLat) / 2), 2) +
                Math.cos(radFromLat) *
                Math.cos(radToLat) *
                Math.pow(Math.sin((radFromLng - radToLng) / 2), 2)
            )
        )
    );
}


export default function computeDistanceBetween(
    from: LatLng,
    to: LatLng,
    radius: number = EARTH_RADIUS
) {
    return computeDistanceBetweenHelper(from, to) * radius;
}
