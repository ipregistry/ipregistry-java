# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [6.0.0] - 2025-11-26
### Changed
- **BREAKING:** Require Java 25+.

## [5.0.1] - 2025-07-22

Minor release to fix an issue in the new automated workflow.

## [5.0.0] - 2025-07-22
### Changed
- **BREAKING:** Require Java 21+.
- Fixed deprecated Gradle configurations.

### Improved
- Added professional, comprehensive javadoc documentation to all API model classes.

### Removed
- Remove deprecated `IpregistryConfig#apiUrl`. Use `IpregistryConfig#baseUrl` instead.

## [4.0.0] - 2023-05-08
### Added
- Add new `IpregistryGeolocationDataset` class for easy loading and query an Ipregistry Geolocation dataset offline.
### Changed
- Updated required Java version to Java 11 from Java 8.

## [3.7.0] - 2022-06-10
### Added
- Add new `parse` method to _IpregistryClient_ to parse one or more user-agent values.

## [3.6.0] - 2022-04-15
### Added
- New `vpn` field in `security` object.

## [3.5.0] - 2021-12-21
### Added
- New `relay` field in `security` object.
### Deprecated
- Deprecate `IpregistryConfigBuilder.apiUrl` in favor of `IpregistryConfigBuilder.baseUrl`.

## [3.4.0] - 2021-11-07
### Added
- Support for usage in native images with GraalVM.

## [3.3.0] - 2021-10-26
### Added
- _IpInfo_ responses has a new `company` field.
- The enum _ConnectionType_ includes a new value of `INACTIVE`.
- The class _DefaultRequestHandler_ accepts a custom _ObjectMapper_.
### Changed
- A single _ObjectMapper_ instance is created and reused in _DefaultRequestHandler_.
- The API key is no longer passed as a query parameter but as a header when a request is sent.

## [3.2.1] - 2021-07-26
### Changed
- Restore missing getters in _Region_ model class.

## [3.2.0] - 2021-07-26
### Added
- Add setters to all model classes.
- New connection type `GOVERNMENT`.
### Changed
- Improve utility method to detect bots/crawlers/spiders based on user-agent value.
- Parsing of unknown connection type values fallbacks to `BUSINESS`.
- Upgrade dependencies.
### Removed
- Merge connection type `CDN` with `HOSTING`.

## [3.1.2] - 2021-04-08
### Changed
- Upgrade dependencies

## [3.1.1] - 2020-09-03
### Changed
- Compatibility level is set to Java 8.
### Fixed
- Fix broken lookup when InetAddress is used as input parameter. 

## [3.1.0] - 2020-07-05
### Changed
- Upgrade dependencies.
- Increase default timeout values to 15s from 2s.

## [3.0.0] - 2020-04-16
### Changed
- [BREAKING] Rename `DefaultCache` to `InMemoryCache`.
- Upgrade dependencies
### Fixed
- Fix a `ClassCastException` that was raised when a valid remote resource is used as lookup input (e.g. robots.txt).

## [2.3.0] - 2020-01-12
### Added
- New field `connection.route` in response model.
- Introduce new value `cdn` for field `connection.type`.
### Fixed
- Change type _Integer_ to _Long_ for Autonomous System number associated with field `connection.asn`.

## [2.2.0] - 2019-10-27
### Added
- New currency fields `name_native` and `plural_native`.
### Changed
- Decrease the default cache period to 10min from 24h. 
This is to better handle use cases that require fresh [security data](https://ipregistry.co/docs/proxy-tor-threat-detection#content). 
Indeed, such data is updated multiple times each hour. 
You can still configure the cache period to a higher value:
https://github.com/ipregistry/ipregistry-java#caching

## [2.1.1] - 2019-08-08
### Fixed
- Lombok warnings (equals not calling super and builder ignoring default value).

## [2.1.0] - 2019-08-08
### Added
- Error code [FORBIDDEN_IP_ORIGIN](https://ipregistry.co/docs/errors#FORBIDDEN_IP_ORIGIN).
- Carrier class and field.
- Connection domain field.
- Connection type field and enum.
- Security is_cloud_provider field.

### Changed
- Implement equals/hashCode in all IpInfo subfield classes.
- Default cache implementation will expire entries older than 24 hours by default.
- Allow passing any int value to `expireAfter` and `maximumSize` parameters of DefaultCache constructor.

## [2.0.0] - 2019-07-19
### Added
- Changelog file.
- Manifest file with build information on generated JAR.
- Missing file headers.
- Unknown IP type.
- Utility class `IpregistryOptions`.

### Changed
- Rename `EmptyCache` class to `NoCache`.
- Rename `IpData`* classes to `IpInfo`*.
- Rename `Ipregistry` class to `IpregistryClient`.
- Use all lookup parameters (including options) to cache results.

### Removed
- Remove docs/ folder.

### Fixed
- Fix NPE with origin lookup.

## [1.1.0] - 2019-07-03
- Increased version number for testing purposes.

## [1.0.0] - 2019-07-03
- First public release.

[Unreleased]: https://github.com/ipregistry/ipregistry-java/compare/v6.0.0...HEAD
[6.0.0]: https://github.com/ipregistry/ipregistry-java/compare/v5.0.1...v6.0.0
[5.0.1]: https://github.com/ipregistry/ipregistry-java/compare/v5.0.0...v5.0.1
[5.0.0]: https://github.com/ipregistry/ipregistry-java/compare/v4.0.0...v5.0.0
[4.0.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.7.0...v4.0.0
[3.7.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.6.0...v3.7.0
[3.6.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.5.0...v3.6.0
[3.5.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.4.0...v3.5.0
[3.4.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.3.0...v3.4.0
[3.3.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.2.1...v3.3.0
[3.2.1]: https://github.com/ipregistry/ipregistry-java/compare/v3.2.0...v3.2.1
[3.2.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.1.2...v3.2.0
[3.1.2]: https://github.com/ipregistry/ipregistry-java/compare/v3.1.1...v3.1.2
[3.1.1]: https://github.com/ipregistry/ipregistry-java/compare/v3.0.0...v3.1.1
[3.1.0]: https://github.com/ipregistry/ipregistry-java/compare/v3.0.0...v3.1.0
[3.0.0]: https://github.com/ipregistry/ipregistry-java/compare/v2.3.0...v3.0.0
[2.3.0]: https://github.com/ipregistry/ipregistry-java/compare/v2.2.0...v2.3.0
[2.2.0]: https://github.com/ipregistry/ipregistry-java/compare/v2.1.1...v2.2.0
[2.1.1]: https://github.com/ipregistry/ipregistry-java/compare/v2.1.0...v2.1.1
[2.1.0]: https://github.com/ipregistry/ipregistry-java/compare/v2.0.0...v2.1.0
[2.0.0]: https://github.com/ipregistry/ipregistry-java/compare/v1.1.0...v2.0.0
[1.1.0]: https://github.com/ipregistry/ipregistry-java/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/ipregistry/ipregistry-java/releases/tag/v1.0.0
