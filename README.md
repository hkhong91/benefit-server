Benefit Server
===========

To run the application, you need to install DBs.

<pre>
docker-compose -f up -d
</pre>

## Components

### Coupon

* Manage coupon benefits, issuance, and usage policies.
* Implement loose, strict, transactional level validation.

### Point

* Shows the user the exact point amount.
* Calculate the order of use based on the points expiration policy.